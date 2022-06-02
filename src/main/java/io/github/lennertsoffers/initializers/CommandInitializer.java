package io.github.lennertsoffers.initializers;

import io.github.lennertsoffers.annotations.NamedCommandExecutor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;

/**
 * Initializes all classes annotated with @NamedCommandExecutor and registers them to the server
 */
public class CommandInitializer extends Initializer {
    public CommandInitializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation, Class<?> type) {
        super(plugin, reflections, annotation, type);
    }

    @Override
    protected void initialize(Class<?> clazz, Object object) {
        NamedCommandExecutor scheduledBukkitRunnableAnnotation = clazz.getAnnotation(NamedCommandExecutor.class);
        String commandName = scheduledBukkitRunnableAnnotation.commandName();

        // Get the command registered in the plugin.yml file
        PluginCommand pluginCommand = this.getPlugin().getCommand(commandName);

        if (pluginCommand != null) {
            // Set the instance of the class to handle this command
            pluginCommand.setExecutor((CommandExecutor) object);
        }
    }
}
