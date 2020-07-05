package org.com.liurz.iresources.servcie;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * 
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

   /**
    *
    *
    */
    public void testApp()
    {
        assertTrue( true );
        int i = 20;
        ArrayList<String> lists = new ArrayList<String>();
        if (lists != null) {
            if (lists.size()>9) {
                
            }
        }
        for (int i1 = 0; i1 < lists.size(); i1++) {
            
        }

    }
}
