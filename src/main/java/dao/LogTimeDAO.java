package dao;

import action.common.Constrants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理计费的类 这里提供了简单的插入方法
 * @author mk
 */
public class LogTimeDAO extends AbstractAdminDAO
{
    @Override
    String getClassName()
    {
        return "logTime";
    }

    public final void insertLoginTime(int TNo, Timestamp loginTime)
    {
        insert("TNo",TNo,"LoginTime",loginTime,"LogoutTime", Constrants.ORIGINTIME);
    }

    public final void insertLogoutTime(int Tno,Timestamp logoutTime)
    {
        ResultSet set =query("Tno",Tno);
        List<Integer> list=new ArrayList<Integer>();
        try
        {
            while (set.next())
            {
                list.add(set.getInt(1));
            }
        }
        catch (SQLException e)
        {
            getLogger().error("Error in getInt",e);
            throw new RuntimeException();
        }
        int last=list.get(list.size()-1);

        update("logoutTime",logoutTime,last);
    }
}
