package dao;

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

}
