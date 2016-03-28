package filter.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

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

}
