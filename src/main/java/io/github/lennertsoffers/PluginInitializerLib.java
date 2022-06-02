package io.github.lennertsoffers;

import io.github.lennertsoffers.annotations.EventListener;
import io.github.lennertsoffers.annotations.NamedCommandExecutor;
import io.github.lennertsoffers.annotations.ScheduledBukkitRunnable;
import io.github.lennertsoffers.initializers.CommandInitializer;
import io.github.lennertsoffers.initializers.EventInitializer;
import io.github.lennertsoffers.initializers.Initializer;
import io.github.lennertsoffers.initializers.RunnableInitializer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.util.List;

public class PluginInitializerLib {
    /**
     * Registers all classes annotated with:
     * <ul>
     *     <li>@EventListener as an eventListener</li>
     *     <li>@NamedCommandExecutor as a command for the given name</li>
     * </ul>
     * @param plugin the plugin instance
     * @param root the path where to look for annotated classes
     */
    public static void init(final JavaPlugin plugin, final String root) {
        Reflections reflections = new Reflections(root);

        List.of(
            new CommandInitializer(plugin, reflections, NamedCommandExecutor.class, CommandExecutor.class),
            new EventInitializer(plugin, reflections, EventListener.class, Listener.class),
            new RunnableInitializer(plugin, reflections, ScheduledBukkitRunnable.class, BukkitRunnable.class)
        ).forEach(Initializer::init);
    }
}
