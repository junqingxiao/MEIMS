package manager;

import org.apache.log4j.Logger;

/**
 * @author mk
 * Manager 返回de数据都是List
 */
public abstract class AbstractManager
{
    /**
     * 日志
     */
    private Logger logger=null;

    final Logger getLogger()
    {
        if (logger == null)
        {
            logger = Logger.getLogger(this.getClass().getName());
        }
        return logger;
    }
}
