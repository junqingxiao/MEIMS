package data;

import java.util.Date;

/**
 * @author mk
 */
public class EPChange
{
    private String pName;
    private String dName;
    private Date date;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setDName(String dName)
    {
        this.dName = dName;
    }

    public String getPName()
    {
        return pName;
    }

    public String getDName()
    {
        return dName;
    }

    public void setPName(String pName)
    {
        this.pName = pName;
    }
}
