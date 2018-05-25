package com.analysis.shared.app.service;

import com.analysis.shared.app.exceptions.ReadFileException;

public interface AnalyticsDataService {

	String getUniqueWords(String[] fileNames) throws ReadFileException;

}
