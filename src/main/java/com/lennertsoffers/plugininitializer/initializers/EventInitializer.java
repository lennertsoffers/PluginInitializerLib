package com.lennertsoffers.plugininitializer.initializers;

import com.lennertsoffers.plugininitializer.annotations.EventListener;
import com.lennertsoffers.plugininitializer.exceptions.ImproperAnnotationException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * Initializes all classes annotated with @EventListener and registers them to handle the events
 */
public class EventInitializer extends Initializer {

    public EventInitializer(JavaPlugin plugin, String root) {
        super(plugin, root);
    }

    @Override
    public void initialize() {
        // Get all the classes that are annotated with the @EventListener annotation
        Set<Class<?>> listenerClasses = new Reflections(this.getRoot()).getTypesAnnotatedWith(EventListener.class);

        try {
            for (Class<?> listenerClass : listenerClasses) {
                // Create an instance of the class
                Object listenerObject = listenerClass.getConstructor().newInstance();

                if (listenerObject instanceof Listener listener){
                    // Register the listener object as an eventListener to the server
                    this.getPlugin().getServer().getPluginManager().registerEvents(listener, this.getPlugin());
                } else {
                    // Throw an exception if the annotated class doesn't implement the Listener interface
                    String className = listenerClass.getName();
                    throw new ImproperAnnotationException("Classes annotated with the NamedCommandExecutor must implement the org.bukkit.event.Listener interface\nThe type of the class with the wrongly placed annotation was " + className);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
