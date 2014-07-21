package com.payback.facecv;

import com.payback.facecv.preprocess.BadInputImageException;
import com.payback.facecv.preprocess.FaceBuilder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TestFaceBuilder
    extends TestCase
{
    public TestFaceBuilder( String testName )
    {
        super( testName );

		System.out.println("Initialized class");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestFaceBuilder.class );
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

	public void testFromImage() {
		try {
			FaceBuilder faceBuilder = new FaceBuilder();

			faceBuilder.fromImage("/test-face.jpg");
		} catch (BadInputImageException e) {
			System.out.println("Test failed. Exception: " + e.toString());
			assertTrue(false);
		}
	}
}
