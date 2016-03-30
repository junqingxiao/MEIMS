package dao;

/**
 * @author mk
 * 处理tenant相关逻辑 用Mtconnector接口
 */

import org.MtConnector.Configuration.MtConfiguration;
import org.MtConnector.Session.MtResultSet;
import org.MtConnector.Session.MtSession;

abstract class AbstractTenantDAO extends AbstractDAO<MtResultSet>
{
    /**
     * 配置文件路径
     */
    private static final String xmlPath="src/main/resources/MtConfiguration.xml";

    private MtSession session;

    @Override
    public final void init(String tenantIdentifier)
    {
        MtConfiguration configuration=new MtConfiguration(xmlPath);
        configuration.configure();

        session=configuration.openSessionWithTenantIdentifier(tenantIdentifier);
    }

    /**
     *根据2属性插入一条纪录,主键自增
     */
    @Override
    public final void insert(String name1,Object value1,String name2,Object value2)
    {
        String sql="insert into "+getClassName()+"("+name1+","+name2+") values('"+value1+"','"+value2+"')";
        session.executeUpdate(sql);
    }

    /**
     *根据3属性插入一条纪录,主键自增
     */
    @Override
    public final void insert(String name1,Object value1,String name2,Object value2,String name3,Object value3)
    {
        String sql="insert into "+getClassName()+"("+name1+","+name2+","+name3+") values('"+value1+"','"+value2+"','"+value3+"')";
        session.executeUpdate(sql);
    }


    @Override
    public final void close()
    {
        session.close();
    }

    /**
     * 根据No删除一条纪录
     * @param no 主键
     */
    @Override
    public final void delete(int no)
    {
        String sql="delete from "+getClassName()+" where No="+no;
        session.executeUpdate(sql);
    }

    /**
     * 根据2属性删除一条纪录
     */
    @Override
    public final void delete(String name1,Object value1,String name2,Object value2)
    {
        String sql="delete from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"'";
        session.executeUpdate(sql);
    }

    /**
     * 根据3属性删除一条纪录
     */
    @Override
    public final void delete(String name1,Object value1,String name2,Object value2,String name3,Object value3)
    {
        String sql="delete from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"' and "+name3+"='"+value3+"'";
        session.executeUpdate(sql);
    }

    /**
     * 根据一个属性查询
     * @param name 属性名
     * @param value 属性值
     * @return MtResultSet集合
     */
    @Override
    public final MtResultSet query(String name,Object value)
    {
        String sql="select * from "+getClassName()+" where "+name+"='"+value+"'";
        return session.executeQuery(sql);
    }

    /**
     * 根据2个属性查询
     */
    @Override
    public final MtResultSet query(String name1,Object value1,String name2,Object value2)
    {
        String sql="select * from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"'";
        return session.executeQuery(sql);
    }

    /**
     * 根据3个属性查询
     */
    @Override
    public final MtResultSet query(String name1,Object value1,String name2,Object value2,String name3,Object value3)
    {
        String sql="select * from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"' and "+name3+"='"+value3+"'";
        return session.executeQuery(sql);
    }

    /**
     *根据主键查询
     */
    @Override
    public final MtResultSet query(int no)
    {
        return query("No",no);
    }

    /**
     * 查询数目
     */
    @Override
    public final MtResultSet count()
    {
        String sql="select count(*) from "+getClassName();
        return session.executeQuery(sql);
    }

    /**
     * 查询所有
     */
    @Override
    public final MtResultSet queryAll()
    {
        String sql="select * from "+getClassName();
        return session.executeQuery(sql);
    }

    /**
     * 根据No更新一个属性
     * @param name 属性名
     * @param value 属性值
     * @param no No
     */
    @Override
    public final void update(String name,Object value,int no)
    {
        String sql="update "+getClassName()+" set "+name+"='"+value+"' where No="+no;
        session.executeUpdate(sql);
    }

    /**
     *根据No更新2个属性
     */
    @Override
    public final void update(String name1,Object value1,String name2,Object value2,int no)
    {
        String sql="update "+getClassName()+" set "+name1+"='"+value1+"' , "+name2+"='"+value2+"' where No="+no;
        session.executeUpdate(sql);
    }

    /**
     *根据No更新3个属性
     */
    @Override
    public final void update(String name1,Object value1,String name2,Object value2,String name3,Object value3,int no)
    {
        String sql="update "+getClassName()+" set "+name1+"='"+value1+"' , "+name2+"='"+value2+"' , "+name3+"='"+value3+"' where No="+no;
        session.executeUpdate(sql);
    }
}
