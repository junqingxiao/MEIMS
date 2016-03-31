package action.app.admin;

import action.common.CommonAction;
import filter.log.Log4admin;
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
        adminManager.updateAdminPassword(getSessionName(),password);
        adminManager.close();

        getLogger().info(getSessionName()+" update it`s password.");

            //应用日志处理
            Log4admin log4admin=new Log4admin();
            log4admin.log(getSessionName()+" 修改了自己的密码,新密码:"+password);

            return SUCCESS;
    }
}

