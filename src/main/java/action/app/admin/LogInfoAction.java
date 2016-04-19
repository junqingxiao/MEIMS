package action.app.admin;

import action.common.CommonAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

/**
 * @author mk
 */
public class LogInfoAction extends CommonAction
{
    private List<String> text=new ArrayList<String>();
    private Date date=new Date();
    private String dateString;

    public List<String> getText()
    {
        return text;
    }

    public String getDateString()
    {
        return dateString;
    }

    public String execute() throws Exception
    {
        // 按照时间 今天的就是admin.log
        File file=new File(System.getProperty("user.dir")+ "/log/admin/admin.log");
        BufferedReader reader;
        try
        {
            reader=new BufferedReader(new FileReader(file));
            String tmpString;
            while ((tmpString = reader.readLine()) != null)
            {
                text.add(tmpString);
            }
        }
        catch (IOException e)
        {
            getLogger().error("could not read log file.");
        }
        //返回今天的日期
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        dateString=DateFormat.getDateInstance(DateFormat.LONG).format(date);
        return SUCCESS;
    }
}
