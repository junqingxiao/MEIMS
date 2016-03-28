package data;

/**
 * @author mk
 * 数据的对象 用来服务器向客户端传递
 * no,name,password
 */
public class Tenant
{
    private int no;
    private String name;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


