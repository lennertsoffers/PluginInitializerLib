package com.lennertsoffers.plugininitializer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated class is a CommandExecutor.
 * The command name is the name or alias of the command you registered in the plugin.yml file.
 * All classes indicated with the CommandExecutor annotation will be automatically registered.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NamedCommandExecutor {
    String commandName();
}
