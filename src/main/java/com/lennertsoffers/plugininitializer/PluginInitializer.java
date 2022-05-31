package com.lennertsoffers.plugininitializer;

import com.lennertsoffers.plugininitializer.initializers.CommandInitializer;
import com.lennertsoffers.plugininitializer.initializers.EventInitializer;
import com.lennertsoffers.plugininitializer.initializers.Initializer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PluginInitializer {
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
        List.of(
            new CommandInitializer(plugin, root),
            new EventInitializer(plugin, root)
        ).forEach(Initializer::initialize);
    }
}
