package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;

/**
 * @author mk
 */
public class PositionDeleteAction extends CommonAction
{
    private int dNo;
    private String pName;
    private String dName;

    public void setDName(String dName)
    {
        this.dName = dName;
    }

    public String getPName()
    {
        return pName;
    }

    public String getDName()
    {
        return dName;
    }

    public int getDNo()
    {
        return dNo;
    }

    public void setDNo(int dNo)
    {
        this.dNo = dNo;
    }

    public void setPName(String pName)
    {
        this.pName = pName;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        tenantManager.deletePosition(dNo,pName);
        tenantManager.close();

        getLogger().info(getSessionName()+" delete a position.");

        //应用日志处理
        Log4admin log4admin=new Log4admin();
        log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 删除了的一个职位.");

        Log4tenant log4tenant=new Log4tenant(getSessionNo());
        log4tenant.log("删除了部门["+dName+"]下的一个职位:"+pName);
        return SUCCESS;
    }
}
