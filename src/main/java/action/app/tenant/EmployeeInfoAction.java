package action.app.tenant;

import action.common.CommonAction;
import data.Employee;

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
        return SUCCESS;
    }
}
