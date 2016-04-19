package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;

/**
 * @author mk
 */
public class DepartmentUpdateAction extends CommonAction
{
    private String name;

    private String message;

    private int no;

    public int getNo()
    {
        return no;
    }

    public void setNo(int no)
    {
        this.no = no;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String getMessage()
    {
        return message;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager = new TenantManager(Constrants.PREFIX + getSessionNo());
        if (tenantManager.updateDepartment(name, no))
        {  //已存在
            getLogger().info("the department that " + getSessionName() + " wanted to update is illegal,refused.");
            message = name+"已存在,请换一个名字";
            tenantManager.close();
            return ERROR;
        }
        else
        {
            //日志处理
            getLogger().info(getSessionName() + " update a department.");

            Log4admin log4admin = new Log4admin();
            log4admin.log("[" + getSessionType() + "]  " + getSessionName() + " 更新了一个部门.");

            Log4tenant log4tenant = new Log4tenant(getSessionNo());
            log4tenant.log("更新了一个部门.新名字:" + name);

            return SUCCESS;
        }
    }
}
