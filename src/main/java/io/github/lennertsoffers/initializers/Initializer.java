package io.github.lennertsoffers.initializers;

import io.github.lennertsoffers.exceptions.ImproperAnnotationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * Parent class for all initializers
 */
public abstract class Initializer {
    private final JavaPlugin plugin;
    private final Set<Class<?>> classSet;
    private final String annotation;
    final Class<?> type;

    public Initializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation, Class<?> type) {
        this.plugin = plugin;
        this.type = type;
        this.annotation = annotation.getSimpleName();
        this.classSet = reflections.getTypesAnnotatedWith(annotation);
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    /**
     * Loop the classes in the set and execute the initialize method on them
     */
    public void init() {
        this.classSet.forEach(clazz -> {
            try {
                // Create an instance of the class
                Object listenerObject = clazz.getConstructor().newInstance();

                if (type.isAssignableFrom(clazz)) {
                    this.initialize(clazz, listenerObject);
                } else {
                    StringBuilder stringBuilder = new StringBuilder("Classes annotated with ");
                    stringBuilder.append(this.annotation);
                    stringBuilder.append(" must ");
                    if (clazz.isInterface()) {
                        stringBuilder.append("implement ");
                    } else {
                        stringBuilder.append("extend ");
                    }
                    stringBuilder.append(this.type.getName());
                    stringBuilder.append("\n\tThe type of the class with the wrongly placed annotation was: ");
                    stringBuilder.append(clazz.getSimpleName());

                    String message = stringBuilder.toString();

                    throw new ImproperAnnotationException(message);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Method to be called to initialize a kind of classes
     */
    protected abstract void initialize(Class<?> clazz, Object object);
}
