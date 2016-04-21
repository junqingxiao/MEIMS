package action.app.tenant;

import action.common.CommonAction;
import action.common.Constrants;
import data.EPChange;
import manager.TenantManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mk
 */
public class ShowEPChangeAction extends CommonAction
{
    private String name;
    private List<EPChange> list=new ArrayList<EPChange>();

    public List<EPChange> getList()
    {
        return list;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String execute() throws Exception
    {
        TenantManager tenantManager=new TenantManager(Constrants.PREFIX+getSessionNo());
        list=tenantManager.getEPChange(name);
        tenantManager.close();

        getLogger().info(getSessionName()+" got epchange.");

        return SUCCESS;
    }
}
