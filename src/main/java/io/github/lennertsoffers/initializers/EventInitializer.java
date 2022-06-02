package io.github.lennertsoffers.initializers;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;

/**
 * Initializes all classes annotated with @EventListener and registers them to handle the events
 */
public class EventInitializer extends Initializer {

    public EventInitializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation, Class<?> type) {
        super(plugin, reflections, annotation, type);
    }

    @Override
    protected void initialize(Class<?> clazz, Object object) {
        this.getPlugin().getServer().getPluginManager().registerEvents((Listener) object, this.getPlugin());
    }
}
