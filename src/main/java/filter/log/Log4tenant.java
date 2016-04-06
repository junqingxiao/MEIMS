package filter.log;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
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
     * @param name 生成不同的文件
     */
    public Log4tenant(String name)
    {
        // 生成新的Logger
        // 如果已經有了一個Logger實例返回現有的
        logger = Logger.getLogger(name);
        // 清空Appender。特別是不想使用現存實例時一定要初期化
        logger.removeAllAppenders();
        // 設定Logger級別。
        logger.setLevel(Tenant_LOG_LEVEL);
        // 生成新的Appender
        FileAppender appender = new FileAppender();
        PatternLayout layout = new PatternLayout();
        // log的输出形式
        String conversionPattern = "%-d{yyyy-MM-dd HH:mm:ss}  %m%n";
        layout.setConversionPattern(conversionPattern);
        appender.setLayout(layout);
        // log输出路径
        // 这里使用了环境变量[catalina.home]，只有在tomcat环境下才可以取到
        //String tomcatPath = java.lang.System.getProperty("catalina.home");
        appender.setFile("log/tenant/" + name + ".log");
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
