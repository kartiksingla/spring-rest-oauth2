package com.analysis.shared.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerOauth2Config extends AuthorizationServerConfigurerAdapter {

	public static final String CLIENT_ID = "store-client";

	public static final String CLIENT_SECRET = "store-secret";

	public static final String GRANT_TYPE = "password";

	public static final String AUTHORIZATION_CODE = "authorization_code";

	public static final String REFRESH_TOKEN = "refresh_token";

	public static final String IMPLICIT = "implicit";

	public static final String SCOPE_READ = "read";

	private static final String SCOPE_WRITE = "write";

	private static final String TRUST = "trust";

	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 20; // 1 * 60 * 60;

	private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 40; // 6*60*60

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory().withClient(CLIENT_ID).secret(CLIENT_SECRET).authorizedGrantTypes(GRANT_TYPE)
				.scopes(SCOPE_READ,TRUST).accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS).authorizedGrantTypes(AUTHORIZATION_CODE)
				.scopes(SCOPE_WRITE).accessTokenValiditySeconds(100).refreshTokenValiditySeconds(200);
//		clients.inMemory().withClient(CLIENT_ID).secret(CLIENT_SECRET)
//				.authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
//				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST).accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
//				.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);

//		clients.jdbc(hibernateConfiguration.dataSource()).withClient("sampleClientId").authorizedGrantTypes("implicit")
//				.scopes("read").autoApprove(true).and().withClient("clientIdPassword").secret("secret")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("read");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
	}

}
