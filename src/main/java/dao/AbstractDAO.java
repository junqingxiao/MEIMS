package dao;

import org.apache.log4j.Logger;

/**
 * 基础DAO,这是一个抽象类,请用具体得DAO继承此类
 * T 取决于使用的数据裤接口
 * MtResultSet 对应 MtConnector
 * ResultSet 对应 JDBC
 * @author mk
 */
public abstract class AbstractDAO<T>
{
    /**
     * 获取实体类型,需要继承,内部使用
     */
    abstract String getClassName();

    /**
     * 日志
     */
    private Logger logger=null;

    final Logger getLogger()
    {
        if (logger == null)
        {
            logger = Logger.getLogger(getClassName());
        }
        return logger;
    }

    /**
     * 初始化
     */
    public void init(){};

    public void init(String s){};

    /**
     * 关闭
     */
    public abstract void close();

    /**
     *根据2属性插入一条纪录,主键自增
     */
    public abstract void insert(String name1,Object value1,String name2,Object value2);

    /**
     *根据3属性插入一条纪录,主键自增
     */
    public abstract void insert(String name1,Object value1,String name2,Object value2,String name3,Object value3);

    /**
     * 根据No删除一条纪录
     * @param no 主键
     */
    public abstract void delete(int no);

    /**
     * 根据一个属性查询
     * @param name 属性名
     * @param value 属性值
     * @return MtResultSet/ResultSet集合
     */
    public abstract T query(String name,Object value);

    /**
     * 根据2个属性查询
     */
    public abstract T query(String name1,Object value1,String name2,Object value2);

    /**
     * 根据3个属性查询
     */
    public abstract T query(String name1,Object value1,String name2,Object value2,String name3,Object value3);

    /**
     *根据主键查询
     */
    public abstract T query(int no);

    /**
     *查询数目
     */
    public abstract T count();

    /**
     * 查询所有
     */
    public abstract T queryAll();

    /**
     * 根据No更新一个属性
     * @param name 属性名
     * @param value 属性值
     * @param no No
     */
    public abstract void update(String name,Object value,int no);

    /**
     *根据No更新2个属性
     */
    public abstract void update(String name1,Object value1,String name2,Object value2,int no);

    /**
     *根据No更新3个属性
     */
    public abstract void update(String name1,Object value1,String name2,Object value2,String name3,Object value3,int no);

}
