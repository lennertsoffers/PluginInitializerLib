package com.lennertsoffers.plugininitializer.initializers;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Parent class for all initializers
 */
public abstract class Initializer {
    // The plugin instance
    private final JavaPlugin plugin;
    // The path where to look for annotated classes
    private final String root;

    public Initializer(JavaPlugin plugin, String root) {
        this.plugin = plugin;
        this.root = root;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public String getRoot() {
        return this.root;
    }

    /**
     * Method to be called to initialize a kind of classes
     */
    public abstract void initialize();
}
