package com.codeborne.selenide.logevents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.logevents.LogEvent.EventStatus.FAIL;

public class SelenideLogger {
  private static final Logger LOG = LoggerFactory.getLogger(SelenideLogger.class);

  protected static ThreadLocal<Map<String, LogEventListener>> listeners = new ThreadLocal<>();

  public static void addListener(String name, LogEventListener listener) {
    Map<String, LogEventListener> threadListeners = listeners.get();
    if (threadListeners == null) {
      threadListeners = new HashMap<>();
    }

    threadListeners.put(name, listener);
    listeners.set(threadListeners);
  }

  public static SelenideLog beginStep(String source, String methodName, Object... args) {
    return beginStep(source, readableMethodName(methodName) + "(" + readableArguments(args) + ")");
  }

  static String readableMethodName(String methodName) {
    return methodName.replaceAll("([A-Z])", " $1").toLowerCase();
  }

  static String readableArguments(Object... args) {
    return args == null ? "" :
        (args[0] instanceof Object[]) ? arrayToString((Object[]) args[0]) :
            arrayToString(args);
  }

  private static String arrayToString(Object[] args) {
    return args.length == 1 ? String.valueOf(args[0]) : Arrays.toString(args);
  }

  public static SelenideLog beginStep(String source, String subject) {
    Collection<LogEventListener> listeners = getEventLoggerListeners();

    SelenideLog log = new SelenideLog(source, subject);
    for (LogEventListener listener : listeners) {
      try {
        listener.beforeEvent(log);
      }
      catch (RuntimeException e) {
        LOG.error("Failed to call listener {}", listener, e);
      }
    }
    return log;
  }

  public static void commitStep(SelenideLog log, Throwable error) {
    log.setError(error);
    commitStep(log, FAIL);
  }

  public static void commitStep(SelenideLog log, LogEvent.EventStatus status) {
    log.setStatus(status);

    Collection<LogEventListener> listeners = getEventLoggerListeners();
    for (LogEventListener listener : listeners) {
      try {
        listener.afterEvent(log);
      }
      catch (RuntimeException e) {
        LOG.error("Failed to call listener {}", listener, e);
      }
    }
  }

  private static Collection<LogEventListener> getEventLoggerListeners() {
    if (listeners.get() == null) {
      listeners.set(new HashMap<>());
    }
    return listeners.get().values();
  }


  @SuppressWarnings("unchecked")
  public static <T extends LogEventListener> T removeListener(String name) {
    Map<String, LogEventListener> listeners = SelenideLogger.listeners.get();
    return listeners == null ? null : (T) listeners.remove(name);
  }

  public static void removeAllListeners() {
    SelenideLogger.listeners.remove();
  }

  /**
   * If listener with given name is bound (added) to the current thread.
   *
   * @param name unique name of listener added by method {@link #addListener(String, LogEventListener)}
   * @return true if method {@link #addListener(String, LogEventListener)} with
   *              corresponding name has been called in current thread.
   */
  public static boolean hasListener(String name) {
    Map<String, LogEventListener> listeners = SelenideLogger.listeners.get();
    return listeners != null && listeners.containsKey(name);
  }
}
