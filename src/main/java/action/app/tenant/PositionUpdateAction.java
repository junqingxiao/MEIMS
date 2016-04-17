package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;

/**
 * @author mk
 */
public class PositionUpdateAction extends CommonAction
{
    private String pName;
    private int dNo;
    private String oldPName;
    private String salary;
    private String dName;

    private String message;

    public void setDNo(int dNo)
    {
        this.dNo = dNo;
    }

    public int getDNo()
    {
        return dNo;
    }

    public String getDName()
    {
        return dName;
    }

    public String getPName()
    {
        return pName;
    }

    public void setDName(String dName)
    {
        this.dName = dName;
    }

    public String getMessage()
    {
        return message;
    }

    public String getOldPName()
    {
        return oldPName;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setOldPName(String oldPName)
    {
        this.oldPName = oldPName;
    }

    public void setPName(String pName)
    {
        this.pName = pName;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager = new TenantManager(Constrants.PREFIX + getSessionNo());

        if(tenantManager.updatePosition(pName,oldPName,salary,dNo))
        {  //已存在
            getLogger().info("the position that " + getSessionName() + " wanted to update is illegal,refused.");
            message = pName+"已存在,请换一个名字";
            tenantManager.close();
            return ERROR;
        }
        else
        {
            //日志处理
            getLogger().info(getSessionName() + " update a position.");

            Log4admin log4admin = new Log4admin();
            log4admin.log("[" + getSessionType() + "]  " + getSessionName() + " 更新了一个职位.");

            Log4tenant log4tenant = new Log4tenant(getSessionName());
            log4tenant.log("更新了"+dName+"下的职位:"+oldPName+" 新名字:" + pName+" 新工资:" + salary+".");

            return SUCCESS;
        }
    }
}
