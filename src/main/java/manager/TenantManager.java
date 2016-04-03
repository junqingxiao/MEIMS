package manager;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.EpchangeDAO;
import dao.PositionDAO;
import data.Employee;
import org.MtConnector.Session.MtResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mk
 */
public class TenantManager extends AbstractManager
{
    private EmployeeDAO employeeDAO=new EmployeeDAO();
    private PositionDAO positionDAO=new PositionDAO();
    private DepartmentDAO departmentDAO=new DepartmentDAO();
    private EpchangeDAO epchangeDAO=new EpchangeDAO();

    public TenantManager(String identifier)
    {
        employeeDAO.init(identifier);
        positionDAO.init(identifier);
        departmentDAO.init(identifier);
        epchangeDAO.init(identifier);
    }

    /**
     * 获得employee的列表
     * @return 一个employee对象的list
     * 这里按主键倒序排列 1在最后面
     */
    public final List<Employee> getEmployee()
    {
        List<Employee> list=new ArrayList<Employee>();

        MtResultSet set=employeeDAO.queryAll();
        while (set.next())
        {
            Employee employee=new Employee();
            employee.setNo(set.getInt(1));
            employee.setName(set.getString(2));
            //查询position的名字
            MtResultSet positionSet= positionDAO.query(set.getInt(3));
            positionSet.next();
            employee.setpName(positionSet.getString(2));
            //查询department的名字
            MtResultSet departmentSet=departmentDAO.query(positionSet.getInt(4));
            departmentSet.next();
            employee.setdName(departmentSet.getString(2));

            list.add(0,employee);
        }
        getLogger().info("got employee.");
        return list;
    }

    /**
     * 关闭
     */
    public final void close()
    {
        employeeDAO.close();
        positionDAO.close();
        departmentDAO.close();
        epchangeDAO.close();
    }
}
