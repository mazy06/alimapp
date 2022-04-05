package com.alimapp.userservice;

public class JWTUtils {
    public static final String SECRET = "secret";
    public static final String PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 10 * 60 * 1000;
    public static final long EXPIRE_REFRESH_TOKEN = 30 * 60 * 1000;
}
