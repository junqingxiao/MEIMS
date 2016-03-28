package action.app.admin;

import action.common.CommonAction;
import data.Tenant;
import manager.AdminManager;

import java.util.List;

/**
 * @author mk
 */
public class TenantManageAction extends CommonAction
{
    private List<Tenant> list;

    public List<Tenant> getList() {
        return list;
    }

    public final String execute() throws Exception
    {
        AdminManager adminManager=new AdminManager();
        list=adminManager.getTenant();
        adminManager.close();
        return SUCCESS;
    }
}
