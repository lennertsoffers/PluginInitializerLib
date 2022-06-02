package io.github.lennertsoffers.initializers;

import io.github.lennertsoffers.annotations.NamedCommandExecutor;
import io.github.lennertsoffers.exceptions.ImproperAnnotationException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * Initializes all classes annotated with @NamedCommandExecutor and registers them to the server
 */
public class CommandInitializer extends Initializer {
    public CommandInitializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation) {
        super(plugin, reflections, annotation);
    }

    @Override
    protected void initialize(Class<?> clazz) {
        try {
            // Get the name of the command to be registered
            String commandName = clazz.getAnnotation(NamedCommandExecutor.class).commandName();
            // Create an instance of the class
            Object commandExecutorObject = clazz.getConstructor().newInstance();

            if (commandExecutorObject instanceof CommandExecutor commandExecutor) {
                // Get the command registered in the plugin.yml file
                PluginCommand pluginCommand = this.getPlugin().getCommand(commandName);

                if (pluginCommand != null) {
                    // Set the instance of the class to handle this command
                    pluginCommand.setExecutor(commandExecutor);
                }
            } else {
                // Throw an exception if the annotated class doesn't implement the CommandExecutor interface
                String className = clazz.getName();
                throw new ImproperAnnotationException("Classes annotated with the NamedCommandExecutor must implement the org.bukkit.command.CommandExecutor interface\nThe type of the class with the wrongly placed annotation was " + className);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
