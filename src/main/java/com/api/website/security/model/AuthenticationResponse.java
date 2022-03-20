package com.api.website.security.model;

import com.api.website.model.User;

public class AuthenticationResponse {
    private final String _accessJwt;
    private final String _refreshJwt;
    private final User _user;

    public AuthenticationResponse(String _accessJwt, String _refreshJwt, User _user) {
        this._accessJwt = _accessJwt;
        this._refreshJwt = _refreshJwt;
        this._user = _user;
    }

    public String getAccessJwt() {
        return _accessJwt;
    }

    public String getRefreshJwt() { return _refreshJwt; }

    public User get_user() {
        return _user;
    }
}
