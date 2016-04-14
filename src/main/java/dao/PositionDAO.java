package dao;

import org.MtConnector.Configuration.MtConfiguration;
import org.MtConnector.Session.MtResultSet;
import org.MtConnector.Session.MtSession;

/**
 * @author mk
 */
public class PositionDAO extends AbstractTenantDAO
{
    private MtSession session;

    /**
     * 配置文件路径
     */
    private static final String xmlPath=PositionDAO.class.getClassLoader().getResource("MtConfiguration.xml").getPath();

    @Override
    public final void init(String tenantIdentifier)
    {
        //这里先把父类初始化 以免query等其他功能不能实现
        super.init(tenantIdentifier);

        MtConfiguration configuration=new MtConfiguration(xmlPath);
        configuration.configure();

        session=configuration.openSessionWithTenantIdentifier(tenantIdentifier);
    }

    @Override
    String getClassName()
    {
        return "position";
    }

    /**
     * 重写3属性添加 因为冲突了position()
     */
    @Override
    public final void insert(String name1,Object value1,String name2,Object value2,String name3,Object value3)
    {
        String sql="insert into "+getClassName()+" set "+name1+"='"+value1+"',"+name2+"='"+value2+"',"+name3+"='"+value3+"'";
        session.executeUpdate(sql);
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
