package action.account;

/**
 * @author mk
 */

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.AdminManager;

import java.sql.Timestamp;

public class LoginAction extends CommonAction
{
    private String name;
    private String password;

    //返回给之前页面的消息
    private String message;

    public final String execute()
    {
        //判断账号密码是否正确 Manager
        AdminManager adminManager=new AdminManager();
        String identity=adminManager.getIdentity(name,password);
        //将type存入session
        setSessionType(identity);
        setSessionName(name);
        //如果是tenant 那么存入no
        if (identity.equals(Constrants.TENANT))
        {
            int no=adminManager.getTenantId(name, password);
            Log4tenant log4tenant=new Log4tenant(no);
            log4tenant.log("登录了.");
            Log4admin log4admin=new Log4admin();
            log4admin.log("[tenant]  "+name+" 登录了.");
            //记录登录时间
            adminManager.insertLoginTime(no,new Timestamp(System.currentTimeMillis()));
            //设置session
            setSessionNo(no);
        }
        else if (identity.equals(Constrants.ADMIN))
        {
            Log4admin log4admin=new Log4admin();
            log4admin.log("[admin]  "+name+" 登录了.");
        }
        adminManager.close();
        return identity;
    }

    /**
     * getter 和 setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
