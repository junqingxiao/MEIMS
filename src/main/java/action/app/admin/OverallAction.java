package action.app.admin;

import action.common.CommonAction;
import manager.AdminManager;

/**
 * @author mk
 */
public class OverallAction extends CommonAction
{
    private int tenantNumber;
    private int adminNumber;

    public int getAdminNumber() {
        return adminNumber;
    }

    public int getTenantNumber() {
        return tenantNumber;
    }

    public final String execute() throws Exception
    {
        AdminManager adminManager=new AdminManager();
        tenantNumber=adminManager.getTenantNumber();
        adminNumber=adminManager.getAdminNumber();
        return SUCCESS;
    }
}
