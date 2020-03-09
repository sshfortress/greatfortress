package com.sshfortress.common.util;

public class ContextHolder {

	private static final ThreadLocal<Object> sessionHolder = new ThreadLocal<Object>();

	public static void setConcurrentObject(Object session) {

		sessionHolder.set(session);
	}

	public static Object getConcurrentObject() {

		return sessionHolder.get();
	}

	public static void clearContext() {

		sessionHolder.remove();

	}
}
