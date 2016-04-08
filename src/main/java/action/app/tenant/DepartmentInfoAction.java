package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import data.Department;
import manager.TenantManager;

import java.util.List;

/**
 * @author mk
 */
public class DepartmentInfoAction extends CommonAction
{
    private List<Department> list;

    public List<Department> getList()
    {
        return list;
    }

    public final String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        list=tenantManager.getDepartment();
        tenantManager.close();

        getLogger().info(getSessionName()+" got department.");
        return SUCCESS;
    }
}
