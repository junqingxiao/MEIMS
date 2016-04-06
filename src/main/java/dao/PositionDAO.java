package dao;

import org.MtConnector.Session.MtResultSet;

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
     * @param dNo 部门号
     */
    public void insert(String name, int salary,int dNo)
    {
        insert("Name",name,"Salary",salary,"DNo",dNo);
    }

    /**
     * 由name和dNo得到主键
     */
    public int getNo(String name,int dNo)
    {
        MtResultSet set=query("name",name,"dNo",dNo);
        set.next();
        return set.getInt(1);
    }
}
