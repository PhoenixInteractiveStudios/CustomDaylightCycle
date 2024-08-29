package org.burrow_studios.spigot.cdlc;

import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class WorldTask extends BukkitRunnable {
    private final World world;
    private final double factorDay;
    private final double factorNight;

    private double counterDay;
    private double counterNight;

    public WorldTask(@NotNull World world, double factorDay, double factorNight) {
        this.world = world;
        this.factorDay = factorDay;
        this.factorNight = factorNight;
    }

    @Override
    public void run() {
        if (isDay())
            tickDay();
        else
            tickNight();
    }

    private void tickDay() {
        if (counterDay >= 1.0) {
            // counter reached 1 -> allow tick
            counterDay -= 1.0;
        }

        // don't allow tick if the counter hasn't reached 1 yet
        if (counterDay < 1.0)
            this.world.setFullTime(this.world.getFullTime() - 1);

        // jump ahead if counterDay is still greater than 1
        int jumpTicks = (int) counterDay;
        counterDay += jumpTicks;
        if (jumpTicks > 0)
            this.world.setFullTime(this.world.getFullTime() + jumpTicks);

        // increase counter
        counterDay += factorDay;
    }

    private void tickNight() {
        if (counterNight >= 1.0) {
            // counter reached 1 -> allow tick
            counterNight -= 1.0;
        }

        // don't allow tick if the counter hasn't reached 1 yet
        if (counterNight < 1.0)
            this.world.setFullTime(this.world.getFullTime() - 1);

        // jump ahead if counterDay is still greater than 1
        int jumpTicks = (int) counterNight;
        counterNight += jumpTicks;
        if (jumpTicks > 0)
            this.world.setFullTime(this.world.getFullTime() + jumpTicks);

        // increase counter
        counterNight += factorNight;
    }

    private boolean isDay() {
        return this.world.getFullTime() < 12000;
    }

    public @NotNull String getWorldName() {
        return this.world.getName();
    }
}
