package com.payback.facecv.preprocess;

// let's fucking wrap their fucking class because they do so little
// fucking work we're basically just using the fucking C API anyway


import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_core.*;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

class BadFeatureCount extends Exception {

}

public class FeatureDetector {
	CascadeClassifier cascade;
	Mat image;
	int maxFeatures;
	Rect detected;

	public FeatureDetector(CascadeClassifier _cascade, Mat _image,
						   Rect imageROI,
						   int _maxFeatures)
		throws BadFeatureCount {

		cascade = _cascade;

		if(imageROI == null) {
			image = _image;
		} else {
			image = new Mat(_image, imageROI);
		}

		maxFeatures = _maxFeatures;

		detected = new Rect();
		cascade.detectMultiScale(_image, detected);

		if (detected.capacity() > maxFeatures) {
			throw new BadFeatureCount();
		}
	}

	public FeatureDetector(CascadeClassifier _cascade, Mat _image,
						   Rect imageROI)
		throws BadFeatureCount {
		this(_cascade, _image, imageROI, Integer.MAX_VALUE);
	}

	public FeatureDetector(CascadeClassifier _cascade, Mat _image)
		throws BadFeatureCount {

		this(_cascade, _image, null, Integer.MAX_VALUE);
	}


	public int getCount() {
		return detected.capacity();
	}

	public Rect getFeature(int i) {
		return detected.position(i);
	}

	public RectIterator featureIterator() {
		return new RectIterator(detected);
	}

}
