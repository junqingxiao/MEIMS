package data;

import java.util.List;

/**
 * @author mk
 * 部门的数据类 用于前后端传递
 */
public class Department
{
    private int no;
    private String name;
    private List<Position> list;

    public int getNo()
    {
        return no;
    }

    public void setNo(int no)
    {
        this.no = no;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Position> getList()
    {
        return list;
    }

    public void setList(List<Position> list)
    {
        this.list = list;
    }
}
