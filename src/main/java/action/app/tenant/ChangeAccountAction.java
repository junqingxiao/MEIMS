package action.app.tenant;

import action.common.CommonAction;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.AdminManager;

/**
 * @author mk
 */
public class ChangeAccountAction extends CommonAction
{
    private String name;
    private String message;

    public void setName(String newName) {
        this.name = newName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String execute() throws Exception
    {

        AdminManager adminManager=new AdminManager();

        if (adminManager.updateTenantAccount(getSessionName(),name))
        {//如果已经存在
            getLogger().info("the account that "+getSessionName()+" wanted to update is existed,refused..");

            message=name+"已经存在,请换一个名字";
            adminManager.close();
            return ERROR;
        }
        else
        {
            //这里注意要修改sessionName

            getLogger().info(getSessionName()+" update it`s account.");

            //应用日志处理
            Log4admin log4admin=new Log4admin();
            log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 修改了自己的账户,新账户:"+name);

            Log4tenant log4tenant=new Log4tenant(getSessionName());
            log4tenant.log("修改了自己的账户,新账户:"+name);
            setSessionName(name);
            adminManager.close();
            return SUCCESS;
        }
    }
}
