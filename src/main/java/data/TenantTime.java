package data;

import action.common.Constrants;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author mk
 */
public class TenantTime
{
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private long time;
    private double fee;

    /**
     * 将Timestamp转换为String
     */
    private String transfer(Timestamp time)
    {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sdf.format(time);
    }

    public String getFee()
    {
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        return df.format(getTime()* Constrants.FEE);
    }

    /**
     * 相差的分钟数
     */
    public long getTime()
    {
        return (logoutTime.getTime()-loginTime.getTime())/(1000*60);
    }

    public String getLoginTime()
    {
        return transfer(loginTime);
    }

    public String getLogoutTime()
    {
        return transfer(logoutTime);
    }

    public void setLoginTime(Timestamp loginTime)
    {
        this.loginTime = loginTime;
    }

    public void setLogoutTime(Timestamp logoutTime)
    {
        this.logoutTime = logoutTime;
    }
}
