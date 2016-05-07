package manager;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.EpchangeDAO;
import dao.PositionDAO;
import data.Department;
import data.EPChange;
import data.Employee;
import data.Position;
import org.MtConnector.Session.MtResultSet;

import java.util.ArrayList;
import java.util.Date;
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
     * 获得department的数量
     */
    public final int getDepartmentNumber()
    {
        int number;

        MtResultSet set=departmentDAO.count();
        set.next();
        number=set.getInt(1);
        getLogger().info("got department number.");

        return number;
    }

    /**
     * 获得position的数量
     */
    public final int getPositionNumber()
    {
        int number;

        MtResultSet set=positionDAO.count();
        set.next();
        number=set.getInt(1);
        getLogger().info("got position number.");

        return number;
    }

    /**
     * 获得employee的数量
     */
    public final int getEmployeeNumber()
    {
        int number;

        MtResultSet set=employeeDAO.count();
        set.next();
        number=set.getInt(1);
        getLogger().info("got employee number.");

        return number;
    }

    /**
     * 获得一个epchange
     */
    private EPChange getEPChange(int pNo,Date date)
    {
        MtResultSet pSet=positionDAO.query(pNo);//按入职职位号查询position
        pSet.next();
        String PName=pSet.getString(2);
        int DNo=pSet.getInt(4);
        MtResultSet dSet=departmentDAO.query(DNo);
        dSet.next();
        String DName=dSet.getString(2);
        EPChange change=new EPChange();
        change.setDate(date);
        change.setDName(DName);
        change.setPName(PName);

        return change;
    }

    /**
     * 获得 员工 的升迁信息
     */
    public final List<EPChange> getEPChange(String name)
    {
        List<EPChange> list =new ArrayList<EPChange>();

        MtResultSet set= employeeDAO.query("Name",name);
        set.next();
        int eNo=set.getInt(1);//员工的no
        Date entryDate=set.getDate(3);//员工的入职时间

        MtResultSet epSet=epchangeDAO.query("ENo",eNo);
        if (epSet.next())
        {//如果有升迁记录
            //处理入职
            int entryPNo=epSet.getInt(3);//入职职位号
            MtResultSet pSet=positionDAO.query(entryPNo);//按入职职位号查询position
            pSet.next();
            String entryPName=pSet.getString(2);
            int entryDNo=pSet.getInt(4);
            MtResultSet dSet=departmentDAO.query(entryDNo);
            dSet.next();
            String entryDName=dSet.getString(2);
            EPChange entry=new EPChange();
            entry.setDate(entryDate);
            entry.setDName(entryDName);
            entry.setPName(entryPName);
            list.add(0,entry);

            do
            {
                //处理第一次升迁
                list.add(0,getEPChange(epSet.getInt(4),epSet.getDate(5)));
            }
            while (epSet.next());
        }
        else
        {
            list.add(0,getEPChange(set.getInt(4),entryDate));
        }

        return list;
    }

    /**
     * 更新职位信息
     * @return 是否已经存在
     */
    public final boolean updatePosition(String pName,String oldPName,String salary,int dNo)
    {
        MtResultSet set=positionDAO.query("name",pName,"DNo",dNo);
        if (set.next())
        {//如果已经存在
            return true;
        }
        else
        {
            MtResultSet pSet= positionDAO.query("name",oldPName,"DNo",dNo);
            pSet.next();
            int pNo=pSet.getInt(1);
            positionDAO.update("name",pName,"salary",salary,pNo);
            return false;
        }
    }

    /**
     * 根据部门主键 职位名字 删除职位
     */
    public final void deletePosition(int dNo,String pName)
    {
        //这里考虑要把这个职位里的所有员工全删掉
        MtResultSet pSet=positionDAO.query("DNo",dNo,"Name",pName);
        pSet.next();
        int pNo=pSet.getInt(1);
        //删除员工
        employeeDAO.delete("PNo",pNo);
        //删除职位
        positionDAO.delete("No",pNo);
    }

    /**
     *先判断新职位是否合法是否存在 再添加
     * @return 新的部门是否存在 true 已经存在
     */
    public final boolean addPosition(String name,int salary,int dNo)
    {
        MtResultSet set=positionDAO.query("name",name,"dNo",dNo);
        if (set.next())
        {//已经存在这个职位
            return true;
        }
        else
        {
            positionDAO.insert(name,salary,dNo);
            return false;
        }
    }

    /**
     * 根据主键删除部门
     * @param no 主键
     */
    public final void deleteDepartment(int no)
    {
        //这里考虑要把这个部门里的所有职位 员工全删掉
        MtResultSet pSet=positionDAO.query("DNo",no);
        while (pSet.next())
        {
            employeeDAO.delete("PNo",pSet.getInt(1));
        }
        positionDAO.delete("DNo",no);
        departmentDAO.delete(no);
    }

    /**
     * 更新部门信息
     * @return 是否已经存在
     */
    public final boolean updateDepartment(String name,int no)
    {
        MtResultSet set=departmentDAO.query("name",name);
        if (set.next())
        {//如果已经存在
            return true;
        }
        else
        {
            departmentDAO.update("name",name,no);
            return false;
        }
    }

    /**
     *先判断新部门是否合法是否存在 再添加
     * @return 新的部门是否合法
     */
    public final boolean addDepartment(String name)
    {
        MtResultSet set=departmentDAO.query("name",name);
        if (set.next())
        {//已经存在这个部门
            return false;
        }
        else
        {
            departmentDAO.insert(name);
            return true;
        }
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

            if (oldPNo != pNo)
            {//职位不一样才更新升迁纪录
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                epchangeDAO.insert(no,oldPNo,pNo,date);
            }

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
    public final void deleteEmployee(int no)
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
