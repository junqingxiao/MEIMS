package filter.log;

import org.apache.log4j.*;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.net.SyslogAppender;
import org.apache.log4j.pattern.DatePatternConverter;

/**
 * @author mk
 * admin 的log
 */
public class Log4admin
{
    private static Logger logger = Logger.getLogger(Log4admin.class);

    private static class AdminLevel extends Level
    {
        private static final long serialVersionUID =1L;

        private AdminLevel (int level,String name,int sysLogLevel)
        {
            super(level,name,sysLogLevel);
        }
    }

    private static final Level Admin_LOG_LEVEL = new AdminLevel(20050,"Admin", SyslogAppender.LOG_LOCAL0);

    public final void log(Object pm_obLogInfo)
    {
        logger.log(Admin_LOG_LEVEL,pm_obLogInfo);
    }

    public Log4admin()
    {
        // 生成新的Logger
        // 如果已經有了一個Logger實例返回現有的
        logger = Logger.getLogger("admin");
        // 清空Appender。特別是不想使用現存實例時一定要初期化
        logger.removeAllAppenders();
        // 設定Logger級別。
        logger.setLevel(Admin_LOG_LEVEL);
        // 生成新的Appender
        DailyRollingFileAppender appender=new DailyRollingFileAppender();
        PatternLayout layout = new PatternLayout();
        //日志的按天输出
        String datePattern="'.'yyyy-MM-dd";
        appender.setDatePattern(datePattern);
        // log的输出形式
        String conversionPattern = "%-d{yyyy-MM-dd HH:mm:ss}  %m%n";
        layout.setConversionPattern(conversionPattern);
        appender.setLayout(layout);
        // log输出路径
        String tomcatPath = System.getProperty("user.dir");
        appender.setFile(tomcatPath+ "/log/admin/admin.log");
        // log的文字码
        appender.setEncoding("UTF-8");
        // true:在已存在log文件后面追加 false:新log覆盖以前的log
        appender.setAppend(true);
        // 适用当前配置
        appender.activateOptions();
        // 将新的Appender加到Logger中
        logger.addAppender(appender);
    }
}
