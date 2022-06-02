package io.github.lennertsoffers.annotations;

import io.github.lennertsoffers.models.RunMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduledBukkitRunnable {
    RunMethod runMethod();
    boolean async() default false;
    long delay() default -1;
    long period() default -1;
}
