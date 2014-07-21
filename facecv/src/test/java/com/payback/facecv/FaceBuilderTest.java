package com.payback.facecv;

import com.payback.facecv.preprocess.FaceBuilder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class FaceBuilderTest
    extends TestCase
{
    public FaceBuilderTest( String testName )
    {
        super( testName );

		System.out.println("Initialized class");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testInit()
    {
        assertTrue( true );

		FaceBuilder faceBuilder = new FaceBuilder();
		System.out.println("Ran testInit");

    }
}
