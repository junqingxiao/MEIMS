package dao;

/**
 * @author mk
 */
public class PositionDAO extends AbstractTenantDAO
{
    @Override
    String getClassName()
    {
        return "position";
    }

    /**
     *插入一条纪录,主键自增
     * @param name 职位名
     * @param salary 工资
     */
    public void insert(String name, int salary)
    {
        insert("Name",name,"Salary",salary);
    }
}
