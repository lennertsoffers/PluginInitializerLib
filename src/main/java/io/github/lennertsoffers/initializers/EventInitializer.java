package io.github.lennertsoffers.initializers;

import io.github.lennertsoffers.exceptions.ImproperAnnotationException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * Initializes all classes annotated with @EventListener and registers them to handle the events
 */
public class EventInitializer extends Initializer {

    public EventInitializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation) {
        super(plugin, reflections, annotation);
    }

    @Override
    protected void initialize(Class<?> clazz) {
        try {
            // Create an instance of the class
            Object listenerObject = clazz.getConstructor().newInstance();

            if (listenerObject instanceof Listener listener){
                // Register the listener object as an eventListener to the server
                this.getPlugin().getServer().getPluginManager().registerEvents(listener, this.getPlugin());
            } else {
                // Throw an exception if the annotated class doesn't implement the Listener interface
                String className = clazz.getName();
                throw new ImproperAnnotationException("Classes annotated with the NamedCommandExecutor must implement the org.bukkit.event.Listener interface\nThe type of the class with the wrongly placed annotation was " + className);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
