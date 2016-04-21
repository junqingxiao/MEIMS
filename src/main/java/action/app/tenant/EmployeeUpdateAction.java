package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;

/**
 * @author mk
 */
public class EmployeeUpdateAction extends CommonAction
{
    private String name;
    private String pName;
    private String dName;

    private String message;

    private int no;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getPName() {
        return pName;
    }

    public String getDName() {
        return dName;
    }

    public String getName() {
        return name;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getMessage() {
        return message;
    }

    public String execute() throws Exception
    {
        // 16/4/21 这里并没有检查和原来不一样
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        if (tenantManager.updateEmployee(name,pName,dName,no))
        {  //合法

            //日志处理
            getLogger().info(getSessionName()+" update a employee.");

            Log4admin log4admin=new Log4admin();
            log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 更新了一个员工.");

            Log4tenant log4tenant=new Log4tenant(getSessionNo());
            log4tenant.log("更新了一个员工.新名字:"+name+",新职位:"+pName+",新部门:"+dName);

            return SUCCESS;
        }
        else
        {
            getLogger().info("the employee that "+getSessionName()+" wanted to update is illegal,refused.");
            message="非法的职位部门信息";
            tenantManager.close();
            return ERROR;
        }
    }
}
