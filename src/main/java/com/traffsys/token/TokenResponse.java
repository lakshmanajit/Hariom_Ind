package com.traffsys.token;

public class TokenResponse {

	private String accessToken;
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshTokken() {
		return refreshTokken;
	}

	public void setRefreshTokken(String refreshTokken) {
		this.refreshTokken = refreshTokken;
	}

	private String refreshTokken;
	
	public TokenResponse(String accessToken,String refreshTokken)
	{
		this.accessToken=accessToken;
		this.refreshTokken=refreshTokken;
	}
}
