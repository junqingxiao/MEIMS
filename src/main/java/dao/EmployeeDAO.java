package dao;

/**
 * @author mk
 */
public class EmployeeDAO extends AbstractTenantDAO
{
    @Override
    String getClassName()
    {
        return "employee";
    }

    /**
     *插入一条纪录,主键自增
     * @param name 姓名
     * @param entryDate 入职时间
     * @param pNo 职位的No
     */
    public void insert(String name, java.sql.Date entryDate, int pNo)
    {
        insert("Name",name,"EntryDate",entryDate,"PNo",pNo);
    }
}
