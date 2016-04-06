package action.app.tenant;

import action.common.CommonAction;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.AdminManager;

/**
 * @author mk
 */
public class ChangePasswordAction extends CommonAction
{
    private String password;

    public void setPassword(String newName) {
        this.password = newName;
    }

    public String execute() throws Exception
    {

        AdminManager adminManager=new AdminManager();
        adminManager.updateTenantPassword(getSessionName(),password);
        adminManager.close();

        getLogger().info(getSessionName()+" update it`s password.");

        //应用日志处理
        Log4admin log4admin=new Log4admin();
        log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 修改了自己的密码");

        Log4tenant log4tenant=new Log4tenant(getSessionName());
        log4tenant.log("修改了自己的密码,新密码:"+password);

        return SUCCESS;
    }
}
