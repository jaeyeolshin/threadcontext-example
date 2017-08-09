package io.github.jaeyeolshin.threadcontextexample.data;

public class Ad {
    private final String markup;
    private final String log;

    public Ad(String markup, String log) {
        this.markup = markup;
        this.log = log;
    }

    public String getMarkup() {
        return markup;
    }

    public String getLog() {
        return log;
    }
}
