package com.planb.user;

public class UserInfo {
	private static boolean keepLogin;

	public static boolean isKeepLogin() {
		return keepLogin;
	}

	public static void setKeepLogin(boolean keepLogin) {
		UserInfo.keepLogin = keepLogin;
	}
}
