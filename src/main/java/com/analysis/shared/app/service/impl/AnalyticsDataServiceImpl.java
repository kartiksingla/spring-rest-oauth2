package com.analysis.shared.app.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.analysis.shared.app.exceptions.ReadFileException;
import com.analysis.shared.app.service.AnalyticsDataService;
import com.analysis.shared.app.utils.IOUtils;

@Service
public class AnalyticsDataServiceImpl implements AnalyticsDataService {

	private static final Logger LOGGER = Logger.getLogger(AnalyticsDataServiceImpl.class);
	
	@Override
	public String getUniqueWords(String[] fileNames) throws ReadFileException {
		String[] fileData = new String[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			try {
				fileData[i] = IOUtils.readDataFromFile(fileNames[i], true).toLowerCase();
			} catch (ReadFileException e) {
				LOGGER.error(e.getLocalizedMessage());
				throw e;
			}
		}
		UniqueData uniqueData = removeDuplicates(fileData[0].split(" "));
		StringBuilder resultBuilder = new StringBuilder();
		boolean exists;
		for (int i = 0; i < uniqueData.count; i++) {
			exists = true;
			for (int j = 1; j < fileData.length; j++) {
				if(!fileData[j].contains(uniqueData.words[i])){
					exists = false;
					break;
				}
			}
			if (exists) {
				resultBuilder.append(uniqueData.words[i]).append(" ");
			}
		}
		return resultBuilder.toString();
	}
	
	private UniqueData removeDuplicates(String[] tempString) {
		String[] tempArray = new String[tempString.length];
		int indexLocation = 0;
		boolean unique;
		for (int i = 0; i < tempString.length; i++) {
			unique = true;

			for (int j = 0; j < indexLocation; j++)
				if (tempString[i].equals(tempArray[j])) {
					unique = false;
					break;
				}

			if (unique) {
				tempArray[indexLocation] = tempString[i];
				indexLocation++;
			}
		}
		
		return new UniqueData(tempArray, indexLocation);
	}
	
	class UniqueData {
		String[] words;
		int count;
		public UniqueData(String[] words, int count) {
			super();
			this.words = words;
			this.count = count;
		}
		
	}

}
