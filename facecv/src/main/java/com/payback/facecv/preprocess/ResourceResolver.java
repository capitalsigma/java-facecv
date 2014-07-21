package com.payback.facecv.preprocess;

public class ResourceResolver {
	String partialPath;
	String fullPath;

	public ResourceResolver(String _partialPath) {
		partialPath = _partialPath;
	}

	// answers.opencv.org/question/10236/
	static String resolve(String partialPath) {
		URL url = path.getClass().getResource(path);

		return url.getPath();
	}
}
