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

    public Initializer(JavaPlugin plugin, Reflections reflections, Annotation annotation) {
        this.plugin = plugin;
        this.classSet = reflections.getTypesAnnotatedWith(annotation.getClass());
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public Set<Class<?>> getClassSet() {
        return classSet;
    }

    /**
     * Method to be called to initialize a kind of classes
     */
    public abstract void initialize();
}
