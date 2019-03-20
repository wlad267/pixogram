package com.bluementors.security;

public enum AppRoles {

    USER(Names.USER), ADMIN(Names.ADMIN), MENTOR(Names.MENTOR);
    private String name;

    AppRoles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public class Names {
        public static final String USER = "user";
        public static final String MENTOR = "mentor";
        public static final String ADMIN = "admin";
    }
}
