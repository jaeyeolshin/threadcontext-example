package io.github.jaeyeolshin.threadcontextexample.log;

public final class MemoryLog {
    private static final ThreadLocal<StringBuilder> LOGS = ThreadLocal.withInitial(StringBuilder::new);

    public static void append(final String log) {
        LOGS.get().append(log);
    }

    public static String get() {
        final String log = LOGS.get().toString();
        return log;
    }

    public static void clear() {
        LOGS.get().setLength(0);
    }
}
