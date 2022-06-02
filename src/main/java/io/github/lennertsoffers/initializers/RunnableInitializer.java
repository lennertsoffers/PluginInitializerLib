package io.github.lennertsoffers.initializers;

import io.github.lennertsoffers.annotations.ScheduledBukkitRunnable;
import io.github.lennertsoffers.exceptions.ImproperAnnotationException;
import io.github.lennertsoffers.models.RunMethod;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class RunnableInitializer extends Initializer {
    public RunnableInitializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation) {
        super(plugin, reflections, annotation);
    }

    @Override
    protected void initialize(Class<?> clazz) {
        try {
            ScheduledBukkitRunnable scheduledBukkitRunnableAnnotation = clazz.getAnnotation(ScheduledBukkitRunnable.class);

            // Create an instance of the class
            Object bukkitRunnableObject = clazz.getConstructor().newInstance();

            if (bukkitRunnableObject instanceof BukkitRunnable bukkitRunnable) {
                this.runBukkitRunnable(scheduledBukkitRunnableAnnotation, bukkitRunnable);
            } else {
                // Throw an exception if the annotated class doesn't implement the BukkitRunnable interface
                String className = clazz.getName();
                throw new ImproperAnnotationException("Classes annotated with the ScheduledBukkitRunnable must implement the BukkitRunnable interface\nThe type of the class with the wrongly placed annotation was " + className);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void runBukkitRunnable(ScheduledBukkitRunnable scheduledBukkitRunnableAnnotation, BukkitRunnable bukkitRunnable) {
        if (scheduledBukkitRunnableAnnotation.runMethod() == RunMethod.RUN) {
            if (scheduledBukkitRunnableAnnotation.async()) {
                bukkitRunnable.runTask(this.getPlugin());
            } else {
                bukkitRunnable.runTaskAsynchronously(this.getPlugin());
            }
        } else {
            long delay = scheduledBukkitRunnableAnnotation.delay();

            if (scheduledBukkitRunnableAnnotation.runMethod() == RunMethod.RUN_LATER) {
                if (scheduledBukkitRunnableAnnotation.async()) {
                    bukkitRunnable.runTaskLater(this.getPlugin(), delay);
                } else {
                    bukkitRunnable.runTaskLaterAsynchronously(this.getPlugin(), delay);
                }
            } else {
                long period = scheduledBukkitRunnableAnnotation.period();

                if (scheduledBukkitRunnableAnnotation.async()) {
                    bukkitRunnable.runTaskTimer(this.getPlugin(), delay, period);
                } else {
                    bukkitRunnable.runTaskTimerAsynchronously(this.getPlugin(), delay, period);
                }
            }
        }
    }
}
