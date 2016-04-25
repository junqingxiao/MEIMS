package action.account;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import manager.AdminManager;

/**
 * @author mk
 */
public class RegisterAction extends CommonAction
{
    private String name;
    private String password;

    //返回给之前页面的消息
    private String message;

    public final String execute()
    {
        //判断账号密码是否正确 Manager
        AdminManager adminManager=new AdminManager();
        String identity=adminManager.getIdentity(name);

        if (identity.equals(Constrants.NOBODY))
        {//这个账户既不是租户也不是管理员,可以注册
            adminManager.registerTenant(name,password);
            getLogger().info("register tenant.name:"+name+",password:"+password+",waiting for check by admin...");

            Log4admin log4admin =new Log4admin();
            log4admin.log("新用户注册.账号:" +name +",密码:"+password);
            return SUCCESS;
        }
        else
        {
            message=name+"已存在,请尝试新的账号名";

            getLogger().info("the tenant that wanted be registered has exist,refused.");

            return ERROR;
        }
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
