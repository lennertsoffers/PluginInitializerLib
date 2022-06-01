package io.github.lennertsoffers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated class wil be an eventListener.
 * The PluginInitializer will automatically register this event.
 */
@Target(ElementType.TYPE)
public @interface EventListener {
}
