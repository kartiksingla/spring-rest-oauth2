package com.analysis.shared.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.analysis.shared.app.exceptions.ReadFileException;

public class IOUtils {

	public static String readDataFromFile(String fileName, boolean removeSpecialChars) throws ReadFileException {
		File file = new File(fileName);
		StringBuilder strBuilder = new StringBuilder();
		BufferedReader reader = null;
		try {
			String line;
			FileInputStream fileInputStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fileInputStream));
			if (!file.exists()) {
				throw new ReadFileException("File doesn't exist");
			}
			while ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}
			reader.close();
		} catch (IOException exception) {
			throw new ReadFileException("Error occured while reading file." + exception.getLocalizedMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException exception) {
					throw new ReadFileException("Error occured while reading file." + exception.getLocalizedMessage());
				}
			}
		}
		if (removeSpecialChars) {
			Pattern pt = Pattern.compile("[^a-zA-Z0-9 ]");
			Matcher match = pt.matcher(strBuilder);
			return match.replaceAll("");
		}
		return strBuilder.toString();
	}
}
