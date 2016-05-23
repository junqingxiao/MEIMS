package action.account;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.AdminManager;

import java.sql.Timestamp;

/**
 * @author mk
 */
public class LogoutAction extends CommonAction
{
    public final String execute()
    {
        // TODO: 16/4/25 计费 时间
        if (getSessionType().equals(Constrants.TENANT))
        {
            Log4tenant log4tenant=new Log4tenant(getSessionNo());
            log4tenant.log("登出了.");

            //记录登出时间
            AdminManager adminManager=new AdminManager();
            adminManager.insertLogoutTime(getSessionNo(),new Timestamp(System.currentTimeMillis()));
        }
        else if (getSessionType().equals(Constrants.ADMIN))
        {
            Log4admin log4admin=new Log4admin();
            log4admin.log(getSessionName()+"登出了.");
        }

        setSessionType(Constrants.NOBODY);
        setSessionName(Constrants.NOBODY);
        setSessionNo(0);

        getLogger().info("log out.");
        return SUCCESS;
    }
}
