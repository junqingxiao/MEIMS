package filter.log;

import org.apache.log4j.*;
import org.apache.log4j.net.SyslogAppender;

/**
 * @author mk
 */
public class Log4tenant
{
    private static Logger logger;

    private static class TenantLevel extends Level
    {
        private static final long serialVersionUID =1L;

        private TenantLevel (int level,String name,int sysLogLevel)
        {
            super(level,name,sysLogLevel);
        }
    }

    private static final Level Tenant_LOG_LEVEL = new TenantLevel(20049,"Tenant", SyslogAppender.LOG_LOCAL0);

    public final void log(Object pm_obLogInfo)
    {
        logger.log(Tenant_LOG_LEVEL,pm_obLogInfo);
    }

    /**
     * 根据 name 生成不同的logger的构造函数
     * @param no 生成不同的文件
     */
    public Log4tenant(int no)
    {
        // 生成新的Logger
        // 如果已經有了一個Logger實例返回現有的
        logger = Logger.getLogger(Log4tenant.class.getName());
        // 清空Appender。特別是不想使用現存實例時一定要初期化
        logger.removeAllAppenders();
        // 設定Logger級別。
        logger.setLevel(Tenant_LOG_LEVEL);
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

        //这里改为用id为名字存储 避免修改账号之后的错误情况
        String tomcatPath = System.getProperty("user.dir");
        appender.setFile(tomcatPath+ "/log/tenant/" + no + "/"+no+".log");
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
