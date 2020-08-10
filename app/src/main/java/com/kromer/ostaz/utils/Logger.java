package com.kromer.ostaz.utils;

import com.kromer.ostaz.BuildConfig;

import timber.log.Timber;

public final class Logger {

    private Logger() {
        // This utility class is not publicly instantiable
    }

    public static void d(String tag, Object... objects) {
        Timber.d(tag, objects);
    }

    public static void d(String tag, String message) {
        Timber.tag(tag).d(message);
    }

    public static void d(Throwable throwable, String tag, Object... objects) {
        Timber.d(throwable, tag, objects);
    }

    public static void e(String tag, Object... objects) {
        Timber.e(tag, objects);
    }

    public static void e(String tag, String msg) {
        Timber.tag(tag).e(msg);
    }

    public static void e(Throwable throwable, String tag, Object... objects) {
        Timber.e(throwable, tag, objects);
    }

    public static void i(String tag, Object... objects) {
        Timber.i(tag, objects);
    }

    public static void i(String tag, String message) {
        Timber.tag(tag).i(message);
    }

    public static void i(Throwable throwable, String tag, Object... objects) {
        Timber.i(throwable, tag, objects);
    }

    public static void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void w(String tag, String msg) {
        Timber.tag(tag).w(msg);
    }

    public static void w(Throwable throwable, String tag, Object... objects) {
        Timber.w(throwable, tag, objects);
    }
}
