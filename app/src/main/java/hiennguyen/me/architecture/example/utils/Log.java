/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hiennguyen.me.architecture.example.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * API for sending log output.
 * <p/>
 * <p>Generally, use the Log.v() Log.d() Log.i() Log.w() and Log.e()
 * methods.
 * <p/>
 * <p>The order in terms of verbosity, from least to most is
 * ERROR, WARN, INFO, DEBUG, VERBOSE.  Verbose should never be compiled
 * into an application except during development.  Debug logs are compiled
 * in but stripped at runtime.  Error, warning and info logs are always kept.
 * <p/>
 * <p><b>Tip:</b> A good convention is to declare a <code>TAG</code> constant
 * in your class:
 * <p/>
 * <pre>private static final String TAG = "MyActivity";</pre>
 *
 * and use that in subsequent calls to the log methods.
 * </p>
 *
 * <p><b>Tip:</b> Don't forget that when you make a call like
 * <pre>Log.v(TAG, "index=" + i);</pre>
 * that when you're building the string to pass into Log.d, the compiler uses a
 * StringBuilder and at least three allocations occur: the StringBuilder
 * itself, the buffer, and the String object.  Realistically, there is also
 * another buffer allocation and copy, and even more pressure on the gc.
 * That means that if your log message is filtered out, you might be doing
 * significant work and incurring significant overhead.
 */
public final class Log {

    private static final BaseConfig CONFIG = new BaseConfig();

    private static Print print = new Print();

    private Log() {
    }

    /**
     * Send a {@value android.util.Log#VERBOSE} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int v(String tag, String msg) {
        return CONFIG.minimumLogLevel <= android.util.Log.VERBOSE ? android.util.Log.v(tag, msg) : 0;
    }

    /**
     * Send a {@value android.util.Log#VERBOSE} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int v(String tag, String msg, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.VERBOSE ? android.util.Log.v(tag, msg, tr) : 0;
    }

    /**
     * Send a {@value android.util.Log#DEBUG} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int d(String tag, String msg) {
        return CONFIG.minimumLogLevel <= android.util.Log.DEBUG ? android.util.Log.d(tag, msg) : 0;
    }

    /**
     * Send a {@value android.util.Log#DEBUG} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int d(String tag, String msg, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.DEBUG ? android.util.Log.d(tag, msg, tr) : 0;
    }

    /**
     * Send an {@value android.util.Log#INFO} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int i(String tag, String msg) {
        return CONFIG.minimumLogLevel <= android.util.Log.INFO ? android.util.Log.i(tag, msg) : 0;
    }

    /**
     * Send a {@value android.util.Log#INFO} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int i(String tag, String msg, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.INFO ? android.util.Log.i(tag, msg, tr) : 0;
    }

    /**
     * Send a {@value android.util.Log#WARN} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int w(String tag, String msg) {
        return CONFIG.minimumLogLevel <= android.util.Log.WARN ? android.util.Log.w(tag, msg) : 0;
    }

    /**
     * Send a {@value android.util.Log#WARN} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int w(String tag, String msg, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.WARN ? android.util.Log.w(tag, msg, tr) : 0;
    }

    /*
     * Send a {@value android.util.Log#WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.WARN ? android.util.Log.w(tag, tr) : 0;
    }

    /**
     * Send an {@value android.util.Log#ERROR} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
        return CONFIG.minimumLogLevel <= android.util.Log.ERROR ? android.util.Log.e(tag, msg) : 0;
    }

    /**
     * Send a {@value android.util.Log#ERROR} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static int e(String tag, String msg, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.ERROR ? android.util.Log.e(tag, msg, tr) : 0;
    }

    /**
     * What a Terrible Failure: Report a condition that should never happen.
     * The error will always be logged at level ASSERT with the call stack.
     * Depending on system configuration, a report may be added to the
     * {@link android.os.DropBoxManager} and/or the process may be terminated
     * immediately with an error dialog.
     *
     * @param tag Used to identify the source of a log message.
     * @param msg The message you would like logged.
     */
    public static int wtf(String tag, String msg) {
        return CONFIG.minimumLogLevel <= android.util.Log.ASSERT ? android.util.Log.wtf(tag, msg) : 0;
    }

    /**
     * Print methods with formatted string
     */

    /**
     * Print debug log with a formatted string
     * @param s1 message
     * @param args parameters
     */
    public static int dPrintLn(Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.DEBUG) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = args.length > 0 ? String.format(s, args) : s;
        return print.println(android.util.Log.DEBUG, message);
    }

    /**
     * Print debug log with a formatted string
     * @param t exception
     */
    public static int dPrintLn(Throwable t) {
        return CONFIG.minimumLogLevel <= android.util.Log.DEBUG ? print.println(android.util.Log.DEBUG, android.util.Log.getStackTraceString(t)) : 0;
    }

    /**
     * Print debug log with a formatted string
     * @param throwable exception
     * @param s1 message
     * @param args parameters
     */
    public static int dPrintLn(Throwable throwable, Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.DEBUG) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = (args.length > 0 ? String.format(s, args) : s) + '\n' + android.util.Log.getStackTraceString(throwable);
        return print.println(android.util.Log.DEBUG, message);
    }

    /**
     * Print error log with a formatted string
     * @param s1 message
     * @param args parameters
     */
    public static int ePrintLn(Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.ERROR) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = args.length > 0 ? String.format(s, args) : s;
        return print.println(android.util.Log.ERROR, message);
    }

    /**
     * Print error log with a formatted string
     * @param t exception
     */
    public static int ePrintLn(Throwable t) {
        return CONFIG.minimumLogLevel <= android.util.Log.ERROR ? print.println(android.util.Log.ERROR, android.util.Log.getStackTraceString(t)) : 0;
    }

    /**
     * Print error log with a formatted string
     * @param throwable exception
     * @param s1 message
     * @param args parameters
     */
    public static int ePrintLn(Throwable throwable, Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.ERROR) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = (args.length > 0 ? String.format(s, args) : s) + '\n' + android.util.Log.getStackTraceString(throwable);
        return print.println(android.util.Log.ERROR, message);
    }

    /**
     * Print info log with a formatted string
     * @param s1 message
     * @param args parameters
     */
    public static int iPrintLn(Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.INFO) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = args.length > 0 ? String.format(s, args) : s;
        return print.println(android.util.Log.INFO, message);
    }

    /**
     * Print info log with a formatted string
     * @param t exception
     */
    public static int iPrintLn(Throwable t) {
        return CONFIG.minimumLogLevel <= android.util.Log.INFO ? print.println(android.util.Log.INFO, android.util.Log.getStackTraceString(t)) : 0;
    }

    /**
     * Print info log with a formatted string
     * @param throwable exception
     * @param s1 message
     * @param args parameters
     */
    public static int iPrintLn(Throwable throwable, Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.INFO) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = (args.length > 0 ? String.format(s, args) : s) + '\n' + android.util.Log.getStackTraceString(throwable);
        return print.println(android.util.Log.INFO, message);
    }

    /**
     * Print warning log with a formatted string
     * @param s1 message
     * @param args parameters
     */
    public static int wPrintLn(Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.WARN) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = args.length > 0 ? String.format(s, args) : s;
        return print.println(android.util.Log.WARN, message);
    }

    /**
     * Print warning log with a formatted string
     * @param t exception
     */
    public static int wPrintLn(Throwable t) {
        return CONFIG.minimumLogLevel <= android.util.Log.WARN ? print.println(android.util.Log.WARN, android.util.Log.getStackTraceString(t)) : 0;
    }

    /**
     * Print warning log with a formatted string
     * @param throwable exception
     * @param s1 message
     * @param args parameters
     */
    public static int wPrintLn(Throwable throwable, Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.WARN) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = (args.length > 0 ? String.format(s, args) : s) + '\n' + android.util.Log.getStackTraceString(throwable);
        return print.println(android.util.Log.WARN, message);
    }

    /**
     * Print wtf log with a formatted string
     * @param s1 message
     * @param args parameters
     */
    public static int wtfPrintLn(Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.ASSERT) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = args.length > 0 ? String.format(s, args) : s;
        return print.println(android.util.Log.ASSERT, message);
    }

    /**
     * Print wtf log with a formatted string
     * @param t exception
     */
    public static int wtfPrintLn(Throwable t) {
        return CONFIG.minimumLogLevel <= android.util.Log.ASSERT ? print.println(android.util.Log.ASSERT, android.util.Log.getStackTraceString(t)) : 0;
    }

    /**
     * Print wtf log with a formatted string
     * @param throwable exception
     * @param s1 message
     * @param args parameters
     */
    public static int wtfPrintLn(Throwable throwable, Object s1, Object... args) {
        if (CONFIG.minimumLogLevel > android.util.Log.ASSERT) {
            return 0;
        }

        final String s = Print.toString(s1);
        final String message = (args.length > 0 ? String.format(s, args) : s) + '\n' + android.util.Log.getStackTraceString(throwable);
        return print.println(android.util.Log.ASSERT, message);
    }

    /**
     * What a Terrible Failure: Report an exception that should never happen.
     * Similar to {@link #wtf(String, String)}, with an exception to log.
     *
     * @param tag Used to identify the source of a log message.
     * @param tr  An exception to log.
     */
    public static int wtf(String tag, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.ASSERT ? android.util.Log.wtf(tag, tr) : 0;
    }

    /**
     * What a Terrible Failure: Report an exception that should never happen.
     * Similar to {@link #wtf(String, Throwable)}, with a message as well.
     *
     * @param tag Used to identify the source of a log message.
     * @param msg The message you would like logged.
     * @param tr  An exception to log.  May be null.
     */
    public static int wtf(String tag, String msg, Throwable tr) {
        return CONFIG.minimumLogLevel <= android.util.Log.ASSERT ? android.util.Log.wtf(tag, msg, tr) : 0;
    }

    public static int println(int priority, String tag, String msg) {
        return android.util.Log.println(priority, tag, msg);
    }

    public static Config getConfig() {
        return CONFIG;
    }

    public interface Config {
        int getLoggingLevel();
        void setLoggingLevel(int level);
    }

    public static class BaseConfig implements Config {
        protected int minimumLogLevel = android.util.Log.VERBOSE;
        protected String packageName = "";
        protected String scope = "";

        protected BaseConfig() {
        }

        public BaseConfig(Application context) {
            try {
                packageName = context.getPackageName();
                final int flags = context.getPackageManager().getApplicationInfo(packageName, 0).flags;
                minimumLogLevel = (flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0 ? android.util.Log.VERBOSE : android.util.Log.INFO;
                scope = packageName.toUpperCase();
                Log.d(packageName, "Configuring Logging, minimum log level is %" + logLevelToString(minimumLogLevel));
            } catch (Exception e) {
                Log.e(packageName, "Error configuring logger", e);
            }
        }

        @Override
        public int getLoggingLevel() {
            return minimumLogLevel;
        }

        @Override
        public void setLoggingLevel(int level) {
            minimumLogLevel = level;
        }

        public static String logLevelToString(int loglevel) {
            switch (loglevel) {
                case android.util.Log.VERBOSE:
                    return "VERBOSE";
                case android.util.Log.DEBUG:
                    return "DEBUG";
                case android.util.Log.INFO:
                    return "INFO";
                case android.util.Log.WARN:
                    return "WARN";
                case android.util.Log.ERROR:
                    return "ERROR";
                case android.util.Log.ASSERT:
                    return "ASSERT";

                default:
                    return "UNKNOWN";
            }
        }
    }

    /**
     * Default implementation logs to android.util.Log
     */
    private static class Print {
        private static final int DEFAULT_STACK_TRACE_LINE_COUNT = 5;

        public int println(int priority, String msg) {
            return android.util.Log.println(priority, getScope(), processMessage(msg));
        }

        protected String processMessage(String msg) {
            if (CONFIG.minimumLogLevel <= android.util.Log.DEBUG) {
                msg = String.format("%s %s %s", new SimpleDateFormat("HH:mm:ss.SSS", Locale.US).format(System.currentTimeMillis()), Thread.currentThread().getName(), msg);
            }

            return msg;
        }

        protected static String getScope() {
            if (CONFIG.minimumLogLevel <= android.util.Log.DEBUG) {
                final StackTraceElement trace = Thread.currentThread().getStackTrace()[DEFAULT_STACK_TRACE_LINE_COUNT];
                return CONFIG.scope + "/" + trace.getFileName() + ":" + trace.getLineNumber();
            }

            return CONFIG.scope;
        }

        private static String toString(Object obj){
            return obj == null? "" : obj.toString();
        }
    }
}