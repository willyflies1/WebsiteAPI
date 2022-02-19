package com.api.website.security.model;

public class AuthenticationResponse {
    private final String _accessJwt;
    private final String _refreshJwt;

    public AuthenticationResponse(String _accessJwt, String _refreshJwt) {
        this._accessJwt = _accessJwt;
        this._refreshJwt = _refreshJwt;
    }

    public String getAccessJwt() {
        return _accessJwt;
    }

    public String getRefreshJwt() { return _refreshJwt; }
}
