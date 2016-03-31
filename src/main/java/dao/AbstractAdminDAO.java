package dao;

/**
 * @author mk
 * 处理Admin 相关逻辑 使用JDBC接口
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

abstract class AbstractAdminDAO extends AbstractDAO<ResultSet>
{
    //这里直接通过url指定tenant_admin
    private static final String url="jdbc:mysql://localhost:3307/tenant_admin?user=mk&password=123&useUnicode=true&characterEncoding=UTF8";

    private Connection connection;

    @Override
    public final void init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connection=DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException e1)
        {
            getLogger().error("Could not find class:com.mysql.jdbc.Driver",e1);
            throw new RuntimeException();
        }
        catch (SQLException e2)
        {
            getLogger().error("Could not get connection",e2);
            throw new RuntimeException();
        }
    }

    @Override
    public final void close()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            getLogger().error("Could not close connection",e);
            throw new RuntimeException();
        }
    }

    /**
     *根据2属性插入一条纪录,主键自增
     */
    @Override
    public final void insert(String name1,Object value1,String name2,Object value2)
    {
        String sql="insert into "+getClassName()+"("+name1+","+name2+") values('"+value1+"','"+value2+"')";
        executeUpdate(sql);
    }

    /**
     *根据3属性插入一条纪录,主键自增
     */
    @Override
    public final void insert(String name1,Object value1,String name2,Object value2,String name3,Object value3)
    {
        String sql="insert into "+getClassName()+"("+name1+","+name2+","+name3+") values('"+value1+"','"+value2+"','"+value3+"')";
        executeUpdate(sql);
    }

    /**
     * 根据No删除一条纪录
     * @param no 主键
     */
    @Override
    public final void delete(int no)
    {
        String sql="delete from "+getClassName()+" where No="+no;
        executeUpdate(sql);
    }

    /**
     * 根据2属性删除一条纪录
     */
    @Override
    public final void delete(String name1,Object value1,String name2,Object value2)
    {
        String sql="delete from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"'";
        executeUpdate(sql);
    }

    /**
     * 根据3属性删除一条纪录
     */
    @Override
    public final void delete(String name1,Object value1,String name2,Object value2,String name3,Object value3)
    {
        String sql="delete from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"' and "+name3+"='"+value3+"'";
        executeUpdate(sql);
    }


    /**
     * 根据一个属性查询
     * @param name 属性名
     * @param value 属性值
     * @return MtResultSet集合
     */
    @Override
    public final ResultSet query(String name,Object value)
    {
        String sql="select * from "+getClassName()+" where "+name+"='"+value+"'";
        return executeQuery(sql);
    }

    /**
     * 根据2个属性查询
     */
    @Override
    public final ResultSet query(String name1,Object value1,String name2,Object value2)
    {
        String sql="select * from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"'";
        return executeQuery(sql);
    }

    /**
     * 根据3个属性查询
     */
    @Override
    public final ResultSet query(String name1,Object value1,String name2,Object value2,String name3,Object value3)
    {
        String sql="select * from "+getClassName()+" where "+name1+"='"+value1+"' and "+name2+"='"+value2+"' and "+name3+"='"+value3+"'";
        return executeQuery(sql);
    }

    /**
     *根据主键查询
     */
    @Override
    public final ResultSet query(int no)
    {
        return query("No",no);
    }

    /**
     *查询数目
     */
    @Override
    public final ResultSet count()
    {
        String sql="select count(*) from "+getClassName();
        return executeQuery(sql);
    }

    /**
     * 查询所有
     */
    @Override
    public final ResultSet queryAll()
    {
        String sql="select * from "+getClassName();
        return executeQuery(sql);
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
        executeUpdate(sql);
    }

    /**
     *根据No更新2个属性
     */
    @Override
    public final void update(String name1,Object value1,String name2,Object value2,int no)
    {
        String sql="update "+getClassName()+" set "+name1+"='"+value1+"' , "+name2+"='"+value2+"' where No="+no;
        executeUpdate(sql);
    }

    /**
     *根据No更新3个属性
     */
    @Override
    public final void update(String name1,Object value1,String name2,Object value2,String name3,Object value3,int no)
    {
        String sql="update "+getClassName()+" set "+name1+"='"+value1+"' , "+name2+"='"+value2+"' , "+name3+"='"+value3+"' where No="+no;
        executeUpdate(sql);
    }

    /**
     * 创建schema
     */
    public final void createSchema(int id)
    {
        String sql="create database tenant_"+id;

        executeUpdate(sql);
        getLogger().info("create schema:tenant_"+id+".");
    }

    /**
     * 删除SCHEMA
     */
    public final void dropScheme(int id)
    {
        String sql="drop database tenant_"+id;

        executeUpdate(sql);
        getLogger().info("drop schema:tenant_"+id+".");
    }

    /**
     *带异常检测的查询
     */
    private ResultSet executeQuery(String sql)
    {
        try
        {
            Statement stmt=connection.createStatement();
            ResultSet set = stmt.executeQuery(sql);
            getLogger().info("Query executed.");
            return set;
        }
        catch (SQLException e)
        {
            getLogger().error("could not execute Query",e);
            throw new RuntimeException();
        }
    }

    /**
     *带异常检测的更新
     */
    private void executeUpdate(String sql)
    {
        try
        {
            Statement stmt=connection.createStatement();
            stmt.executeUpdate(sql);
            getLogger().info("Update executed.");
        }
        catch (SQLException e)
        {
            getLogger().error("could not execute update",e);
            throw new RuntimeException();
        }
    }
}
