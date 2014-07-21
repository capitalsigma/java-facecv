package com.payback.facecv.preprocess;

import java.io.File;
import java.io.IOError;
import java.net.URL;
import java.util.ArrayList;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Rect;

import com.payback.facecv.elements.Face;

import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_core.*;

// port from:
// docs.opencv.org/doc/tutorials/objdetect/cascade_classifier/cascade_classifier.html

public class FaceBuilder {
	CascadeClassifier faceCascade;
	CascadeClassifier eyeCascade;

	String FACE_CASCADE_PATH = "/haarcascade_frontalface_alt.xml";
	String EYE_CASCADE_PATH = "/haarcascade_eye_tree_eyeglasses.xml";

	public FaceBuilder(){
		// BUG WORKAROUND
		// https://github.com/bytedeco/javacpp/issues/1
		// SERIOUSLY FUCK THIS SHIT
		Loader.load(org.bytedeco.javacpp.opencv_core.class);

		try {
			faceCascade = new CascadeClassifier();
			eyeCascade = new CascadeClassifier();

			faceCascade.load(getResource(FACE_CASCADE_PATH));
			eyeCascade.load(getResource(EYE_CASCADE_PATH));

		} catch (IOError e) {
			System.out.println("Error loading a cascade file: " + e.toString());
		}

		System.out.println("Successfully loaded.");
	}


	public Face[] fromImage(String name) throws BadInputImageException {
		// String path = getResource(name);


		Mat img = imread(path, 0); // load as greyscale
		Rect faceRects = new Rect(); // outparameter because fuck C++
		int faceAreaCount;
		ArrayList<Face> ret = new ArrayList<Face>();

		System.out.println("Got image: " + img.toString());

		if (img.empty()) {
			throw new BadInputImageException();
		}

		// increase contrast to help later processing
		equalizeHist(img, img);

		faceCascade.detectMultiScale(img, faceRects);

		faceAreaCount = faceRects.capacity();
		for(int i = 0; i < faceAreaCount; i++) {
			Rect eyeRects = new Rect();
			Rect faceRect = faceRects.position(i);
			Mat faceROI = new Mat(img, faceRect);

 			System.out.println("Got a faceRect: " + faceRect.toString());
			System.out.println("Got faceROI: " + faceROI.toString());

			eyeCascade.detectMultiScale(faceROI, eyeRects);

			for(int j = 0; j < eyeRects.capacity(); j++) {
				Rect eyeRect = eyeRects.position(j);
				System.out.println("Got eye: " + eyeRect.toString());
			}

		}


		return null;
	}

}
