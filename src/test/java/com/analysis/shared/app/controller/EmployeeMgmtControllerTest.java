package com.analysis.shared.app.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import com.analysis.shared.app.AuthServerOauth2Config;
import com.analysis.shared.app.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
@WebAppConfiguration
public class EmployeeMgmtControllerTest {

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	TestConfig testConfig;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void testHelloApiUnAuth() throws Exception {
		System.out.println(testConfig.getTestVarOne());
		ResultActions perform = mockMvc.perform(get("/employee/hello").accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isUnauthorized()).andExpect(jsonPath("$.error", is("unauthorized")));
	}

	@Test
	public void testHelloApiAuth() throws Exception {
		String token = generateAccessToken("ksingla", "password");
		mockMvc.perform(get("/employee/hello").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

	private String generateAccessToken(String uname, String password) throws Exception {
		String authorization = "Basic " + new String(Base64Utils.encode("store-client:store-secret".getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";

		String content = mockMvc
				.perform(post("/oauth/token").header("Authorization", authorization)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", uname)
						.param("password", password).param("grant_type", AuthServerOauth2Config.GRANT_TYPE)
						.param("client_id", AuthServerOauth2Config.CLIENT_ID)
						.param("client_secret", AuthServerOauth2Config.CLIENT_SECRET))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer")))).andReturn().getResponse()
				.getContentAsString(); // .andExpect(jsonPath("$.expires_in",
										// is(greaterThan(4000)))).andExpect(jsonPath("$.scope",
										// is(equalTo("read write"))))
		System.out.println(content);
		return content.substring(17, 53);
	}
}
