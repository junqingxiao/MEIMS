package action.app.admin;

import action.common.CommonAction;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.AdminManager;

/**
 * @author mk
 */
public class TenantDeleteAction extends CommonAction
{
    private String name;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception
    {
        AdminManager adminManager=new AdminManager();
        adminManager.deleteTenant(name,password);
        adminManager.close();

        getLogger().info(getSessionName()+" delete a tenant.");

        //应用日志处理
        Log4admin log4admin=new Log4admin();
        log4admin.log(getSessionName()+" 删除了一个租户.");
        return SUCCESS;
    }
}
