package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;

/**
 * @author mk
 */
public class DepartmentDeleteAction extends CommonAction
{
    private int no;
    private String name;//用于日志

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        tenantManager.deleteDepartment(no);
        tenantManager.close();

        getLogger().info(getSessionName()+" delete a department.");

        //应用日志处理
        Log4admin log4admin=new Log4admin();
        log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 删除了一个部门.");

        Log4tenant log4tenant=new Log4tenant(getSessionNo());
        log4tenant.log("删除了一个部门:"+name+".");
        return SUCCESS;
    }
}
