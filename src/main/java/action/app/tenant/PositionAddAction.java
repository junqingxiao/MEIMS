package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import filter.log.Log4admin;
import filter.log.Log4tenant;
import manager.TenantManager;

/**
 * @author mk
 */
public class PositionAddAction extends CommonAction
{
    private String name;
    private int salary;
    private int dNo;

    private String dName;
    private String message;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSalary()
    {
        return salary;
    }

    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    public int getDNo()
    {
        return dNo;
    }

    public void setDNo(int dNo)
    {
        this.dNo = dNo;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setDName(String dName)
    {
        this.dName = dName;
    }

    public String getDName()
    {
        return dName;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());

        if (tenantManager.addPosition(name,salary,dNo))
        {  //已经存在

            tenantManager.close();
            getLogger().info("the position that"+getSessionName()+" wanted to add is existed,refused.");
            message=name+"已存在,请尝试其他职位名";
            return ERROR;
        }
        else
        {
            tenantManager.close();

            getLogger().info(getSessionName()+" add a position.");
            //租户日志处理
            Log4tenant log4tenant=new Log4tenant(getSessionName());
            log4tenant.log("为"+dName+"添加了一个职位.新职位名:"+name+",工资:"+salary);

            //admin日志处理
            Log4admin log4admin=new Log4admin();
            log4admin.log("["+getSessionType()+"]  "+getSessionName()+" 添加了一个职位.");
            return SUCCESS;
        }
    }
}
