package filter.log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mk
 */
public class log4AdminTest
{
    @Test
    public void test()
    {
        Log4admin log4admin = new Log4admin();
        log4admin.log("mk test the class");
    }
}