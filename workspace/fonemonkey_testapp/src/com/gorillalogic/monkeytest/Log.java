/*
 * Log.java
 * FoneMonkey Test
 * 
 * Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/06/11.
 * Copyright 2011 Gorilla Logic, Inc. All rights reserved.
 */
package com.gorillalogic.monkeytest;

public class Log
{
	public static String APP_NAME = "FoneMonkey";

    public static void log(String msg)
    {
		android.util.Log.d(APP_NAME, msg);
    }

    public static void log(String msg, Throwable t)
    {
		android.util.Log.d(APP_NAME, msg, t);
    }

    public static void log(Throwable t)
    {
		android.util.Log.d(APP_NAME, t.getMessage() != null ? t.getMessage() : "Error", t);
    }
}

