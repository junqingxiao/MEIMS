package dao;

import org.MtConnector.Session.MtResultSet;

/**
 * @author mk
 */
public class DepartmentDAO extends AbstractTenantDAO
{
    @Override
    String getClassName()
    {
        return "department";
    }

    public final void insert(String name)
    {
        insert("Name",name);
    }

    /**
     * 由主键得到no,要确定存在才使用
     */
    public final int getNo(String name)
    {
        MtResultSet set= query("name",name);
        set.next();
        return set.getInt(1);
    }

}
