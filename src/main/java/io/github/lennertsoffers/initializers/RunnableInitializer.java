package io.github.lennertsoffers.initializers;

import io.github.lennertsoffers.annotations.ScheduledBukkitRunnable;
import io.github.lennertsoffers.models.RunMethod;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;

public class RunnableInitializer extends Initializer {
    public RunnableInitializer(JavaPlugin plugin, Reflections reflections, Class<? extends Annotation> annotation, Class<?> type) {
        super(plugin, reflections, annotation, type);
    }

    @Override
    protected void initialize(Class<?> clazz, Object object) {
            ScheduledBukkitRunnable scheduledBukkitRunnableAnnotation = clazz.getAnnotation(ScheduledBukkitRunnable.class);
            this.runBukkitRunnable(scheduledBukkitRunnableAnnotation, (BukkitRunnable) object);
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
