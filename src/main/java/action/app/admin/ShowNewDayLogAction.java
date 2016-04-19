package action.app.admin;

import action.common.CommonAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mk
 */
public class ShowNewDayLogAction extends CommonAction
{
    private List<String> text = new ArrayList<String>();
    private Date date;
    private String dateString;

    public List<String> getText()
    {
        return text;
    }


    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getDateString()
    {
        return dateString;
    }

    public String execute() throws Exception
    {
        // 16/4/19 和添加员工一样,这里并没有检查日期合法性
        Date today = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        String appendString;
        //将传入日期和今天日期转换后判断是否为同一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(date);
        String ds2 = sdf.format(today);
        if (ds1.equals(ds2))
        {
            appendString = "";
        }
        else
        {
            appendString = "." + (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(date);
        }
        File file = new File(System.getProperty("user.dir") + "/log/admin/admin.log" + appendString );
        BufferedReader reader;
        if (file.exists())
        {
          //存在当天的文件
            try
            {
                reader = new BufferedReader(new FileReader(file));
                String tmpString;
                while ((tmpString = reader.readLine()) != null)
                {
                    text.add(tmpString);
                }
            }
            catch (IOException e)
            {
                getLogger().error("could not read log file..");
            }
        }
        //返回日期
        dateString = DateFormat.getDateInstance(DateFormat.LONG).format(date);
        return SUCCESS;
    }

}
