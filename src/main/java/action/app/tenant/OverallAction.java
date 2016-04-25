package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import manager.TenantManager;

/**
 * @author mk
 */
public class OverallAction extends CommonAction
{
    private int employeeNumber;
    private int positionNumber;
    private int departmentNumber;

    public int getDepartmentNumber()
    {
        return departmentNumber;
    }

    public int getEmployeeNumber()
    {
        return employeeNumber;
    }

    public int getPositionNumber()
    {
        return positionNumber;
    }

    public final String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        employeeNumber=tenantManager.getEmployeeNumber();
        positionNumber=tenantManager.getPositionNumber();
        departmentNumber=tenantManager.getDepartmentNumber();

        tenantManager.close();
        return SUCCESS;
    }
}
