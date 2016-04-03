package dao;

/**
 * @author mk
 */
public class EpchangeDAO extends AbstractTenantDAO
{
    @Override
    String getClassName()
    {
        return "epchange";
    }

    public final void insert(int eNo,int oPNo,int nPno,java.sql.Date date)
    {
        insert("ENo",eNo,"OPNo",oPNo,"NPNo",nPno,"Date",date);
    }
}
