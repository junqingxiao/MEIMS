package action.app.admin;

import action.common.CommonAction;
import data.TenantTime;
import manager.AdminManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mk
 */
public class ShowTenantTimeAction extends CommonAction
{
    private String name;
    private List<TenantTime> list=new ArrayList<TenantTime>();

    public List<TenantTime> getList()
    {
        return list;
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
        AdminManager adminManager=new AdminManager();
        list=adminManager.showTenentTime(name);
        adminManager.close();

        getLogger().info(" got tenantTime.");

        return SUCCESS;
    }
}
