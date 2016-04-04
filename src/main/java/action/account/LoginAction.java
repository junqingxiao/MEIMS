package action.account;

/**
 * @author mk
 */

import action.common.CommonAction;
import action.common.Constrants;
import manager.AdminManager;

public class LoginAction extends CommonAction
{
    private String no;
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
            setSessionNo(adminManager.getTenantId(name,password));
        }
        adminManager.close();
        return identity;
    }

    /**
     * getter 和 setter
     */
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

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
