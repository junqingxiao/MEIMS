package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;

/**
 * @author mk
 */
public class DepartmentAddAction extends CommonAction
{
    private String name;
    private String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());

        if (tenantManager.addDepartment(name))
        {  //合法
            tenantManager.close();

            getLogger().info(getSessionName()+" add a department.");
            //租户日志处理
            Log4tenant log4tenant=new Log4tenant(getSessionNo());
            log4tenant.log("添加了一个部门.新部门名:"+name);

            //admin日志处理
            Log4admin log4admin=new Log4admin();
            log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 添加了一个部门.");
            return SUCCESS;
        }
        else
        {
            tenantManager.close();
            getLogger().info(getSessionName()+" wanted to add a illegal department,refused.");
            message=name+"已存在,请尝试其他部门名";
            return ERROR;
        }
    }
}
