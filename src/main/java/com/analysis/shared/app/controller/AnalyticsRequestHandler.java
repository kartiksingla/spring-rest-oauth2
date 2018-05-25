package com.analysis.shared.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analysis.shared.app.exceptions.ReadFileException;
import com.analysis.shared.app.service.AnalyticsDataService;

@RestController
public class AnalyticsRequestHandler {
	
	private static final Logger LOGGER = Logger.getLogger(AnalyticsRequestHandler.class);

	@Autowired
	private AnalyticsDataService analyticsDataService;
	
	@RequestMapping(value = "/getUniqueWords")
	public ResponseEntity<String> getUniqueWords(@RequestParam(value = "filenames", required = true) String filenames){
		if(filenames == null) {
			return new ResponseEntity<String>("filenames field not provided",HttpStatus.BAD_REQUEST);
		}
		String response = null;
		try {
			response = analyticsDataService.getUniqueWords(filenames.split(";"));
		} catch (ReadFileException e) {
			LOGGER.error("Error occurred : " + e.getMessage());
			return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(response == null){
			return new ResponseEntity<String>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
}
