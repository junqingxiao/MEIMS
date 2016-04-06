package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import data.Employee;
import manager.TenantManager;

import java.util.List;

/**
 * @author mk
 */
public class EmployeeInfoAction extends CommonAction
{
    private List<Employee> list;

    public List<Employee> getList() {
        return list;
    }

    public final String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        list=tenantManager.getEmployee();
        tenantManager.close();
        return SUCCESS;
    }
}
