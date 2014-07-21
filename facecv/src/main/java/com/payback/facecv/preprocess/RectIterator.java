package com.payback.facecv.preprocess;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_core.*;

public class RectIterator implements Iterator<Rect> {
	int currentIndex;
	int maxIndex;
	Rect rects;

	public RectIterator(Rect _rects) {
		rects = _rects;

		currentIndex = 0;
		maxIndex = rects.capacity();
	}

	public Rect next() throws NoSuchElementException {
		if(!hasNext()) {
			throw new NoSuchElementException();
		} else {
			return rects.position(currentIndex++);
		}
	}

	public boolean hasNext() {
		return currentIndex < maxIndex;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
