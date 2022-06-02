package io.github.lennertsoffers.initializers;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Parent class for all initializers
 */
public abstract class Initializer {
    // The plugin instance
    private final JavaPlugin plugin;
    // Set of classes annotated by the annotation
    private final Set<Class<?>> classSet;

    public Initializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation) {
        this.plugin = plugin;
        this.classSet = reflections.getTypesAnnotatedWith(annotation);
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    /**
     * Loop the classes in the set and execute the initialize method on them
     */
    public void init() {
        this.classSet.forEach(this::initialize);
    }

    /**
     * Method to be called to initialize a kind of classes
     */
    protected abstract void initialize(Class<?> clazz);
}
