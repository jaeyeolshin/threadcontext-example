package io.github.jaeyeolshin.threadcontextexample.appender;

import io.github.jaeyeolshin.threadcontextexample.log.MemoryLog;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(name = "MemoryAppender", category = "Core", elementType = "appender", printObject = true)
public class MemoryAppender extends AbstractAppender {

    public MemoryAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    @Override
    public void append(LogEvent logEvent) {
        final String log = new String(getLayout().toByteArray(logEvent));
        MemoryLog.append(log);
    }

    @PluginFactory
    public static MemoryAppender create(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter) {
        if (name == null) {
            LOGGER.error("No name provided for MemoryAppender");
            return null;
        }

        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }

        return new MemoryAppender(name, filter, layout);
    }
}
