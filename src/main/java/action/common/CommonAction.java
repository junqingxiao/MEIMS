package action.common;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

/**
 * @author mk
 * 所有 Action 的父类
 */
public class CommonAction extends ActionSupport
{
    private static final long serialVersionUID = 1L;

    /**
     * 日志
     */
    private Logger logger=null;

    protected Logger getLogger()
    {
        if (logger == null)
        {
            logger = Logger.getLogger(this.getClass().getName());
        }
        return logger;
    }

    /**
     * 取得Session中的各种值
     */
    protected Integer getSessionNo()
    {
        return (Integer) ActionContext.getContext().getSession().get(Constrants.NO);
    }

    protected String getSessionName()
    {
        return (String) ActionContext.getContext().getSession().get(Constrants.NAME);
    }

    protected String getSessionType()
    {
        return (String) ActionContext.getContext().getSession().get(Constrants.TYPE);
    }

    protected void setSessionType(String type)
    {
        ActionContext.getContext().getSession().put(Constrants.TYPE,type);

    }

    protected void setSessionName(String name){ActionContext.getContext().getSession().put(Constrants.NAME,name);}

    protected void setSessionNo(Integer no){ActionContext.getContext().getSession().put(Constrants.NO,no);}
}
