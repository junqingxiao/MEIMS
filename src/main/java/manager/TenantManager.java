package manager;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.EpchangeDAO;
import dao.PositionDAO;
import data.Department;
import data.Employee;
import data.Position;
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
     * 获得部门信息
     */
    public final List<Department> getDepartment()
    {
        List<Department> list=new ArrayList<Department>();

        MtResultSet dSet=departmentDAO.queryAll();
        while (dSet.next())
        {
            Department department=new Department();
            int dNo=dSet.getInt(1);
            String name=dSet.getString(2);
            MtResultSet pSet=positionDAO.query("dNo",dNo);
            List<Position> pList=new ArrayList<Position>();

            while (pSet.next())
            {
                Position position=new Position();
                position.setNo(pSet.getInt(1));
                position.setName(pSet.getString(2));
                position.setSalary(pSet.getInt(3));
                pList.add(0,position);
            }
            department.setNo(dNo);
            department.setName(name);
            department.setList(pList);
            list.add(0,department);
        }

        return list;
    }

    /**
     * 更新员工信息
     */
    public final boolean updateEmployee(String name,String pName,String dName,int no)
    {
        int p=isPDLegal(pName,dName);
        if ( p != 0)
        {//合法
            //先得到原来的pNo
            int oldPNo=employeeDAO.getPNoByNo(no);
            //更新
            int dNo=departmentDAO.getNo(dName);
            int pNo=positionDAO.getNo(pName,dNo);
            employeeDAO.update("name",name,"pNo",pNo,no);
            //这里要更新change信息

            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            epchangeDAO.insert(no,oldPNo,pNo,date);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 根据主键删除员工
     * @param no 主键
     */
    public final void deleteEmplyee(int no)
    {
        employeeDAO.delete(no);
    }


    /**
     *先判断关系是否合法是否存在 再添加
     * @return 新的关系是否合法
     */
    public final boolean addEmployee(String name, String pName, String dName, java.sql.Date date)
    {
        int pNo=isPDLegal(pName,dName);
        if ( pNo != 0)
        {//合法
            employeeDAO.insert(name,date,pNo);
            return true;
        }
        else
        {
            return false;
        }
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
            MtResultSet positionSet= positionDAO.query(set.getInt(4));
            positionSet.next();
            employee.setPName(positionSet.getString(2));
            //查询department的名字
            MtResultSet departmentSet=departmentDAO.query(positionSet.getInt(4));
            departmentSet.next();
            employee.setDName(departmentSet.getString(2));

            list.add(0,employee);
        }
        getLogger().info("got employee.");
        return list;
    }

    /**
     * 判断职位名和部门名关系是否合法
     * @return 职位的no 0表示非法
     */
    private int isPDLegal(String pName,String dName)
    {
        MtResultSet set= departmentDAO.query("name",dName);
        if (set.next())
        {//如果部门表中有这个部门
            MtResultSet set1=positionDAO.query("name",pName,"dNo",set.getInt(1));
            if (set1.next())
            {
                return set1.getInt(1);
            }
            else
            {
                return 0;
            }
        }
        else
        {
            return 0;
        }
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
