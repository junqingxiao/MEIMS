package action.common;

import java.sql.Timestamp;
import java.util.Stack;

/**
 * @author mk
 */
public class Constrants
{
    /**
     * Session中的no,name,type
     */
    public static final String NO="no";
    public static final String NAME="name";
    public static final String TYPE="type";

    /**
     * 3种type
     */
    public static final String TENANT="tenant";
    public static final String ADMIN="admin";
    public static final String NOBODY="nobody";

    /**
     * identifier的前缀
     */
    public static final String PREFIX="tenant_";

    /**
     * 默认的时间
     */
    public static final Timestamp ORIGINTIME=Timestamp.valueOf("2001-01-01 00:00:00");

    public static final double FEE=0.2;
}
