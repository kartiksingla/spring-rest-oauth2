package com.analysis.shared.app.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.analysis.shared.app")
@PropertySource({"classpath:application-test.properties"})/*,"classpath:application-test.properties"*/
public class TestConfig {
	
	@Value("${temp.first.var}")
	private String testVarOne;

	public String getTestVarOne() {
		return testVarOne;
	}
	
	

}
