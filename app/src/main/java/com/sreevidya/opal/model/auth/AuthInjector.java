package com.sreevidya.opal.model.auth;


public class AuthInjector {
    private static AuthSource AUTHSOURCE;

    public static AuthSource getInstance() {
        if (AUTHSOURCE == null) {
            AUTHSOURCE = new AuthSourceImpl();
        }
        return AUTHSOURCE;
    }
}
