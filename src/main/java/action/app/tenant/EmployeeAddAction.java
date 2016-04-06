package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;


/**
 * @author mk
 */
public class EmployeeAddAction extends CommonAction
{
    private String name;
    private String pName;
    private String dName;
    private java.sql.Date date;

    public java.sql.Date getDate() {
        return date;
    }

    // TODO: 16/4/5 不一定能从前端拿到值
    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getDName() {
        return dName;
    }

    public String getMessage() {
        return message;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        if (tenantManager.addEmployee(name,pName,dName,date))
        {  //合法
            tenantManager.close();

            getLogger().info(getSessionName()+" add a employee.");
            //租户日志处理
            Log4tenant log4tenant=new Log4tenant(getSessionName());
            log4tenant.log("添加了一个员工.姓名:"+name+".职位:"+pName+".部门:"+dName+".入职日期"+date);

            //admin日志处理
            Log4admin log4admin=new Log4admin();
            log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 添加了一个员工.");
            return SUCCESS;
        }
        else
        {
            tenantManager.close();
            getLogger().info(getSessionName()+" wanted to add a illegal employee,refused.");
            message="请输入合法的部门职位";
            return ERROR;
        }


    }
}
