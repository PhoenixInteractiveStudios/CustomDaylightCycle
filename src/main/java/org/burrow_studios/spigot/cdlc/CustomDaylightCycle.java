package org.burrow_studios.spigot.cdlc;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class CustomDaylightCycle extends JavaPlugin {
    private Set<WorldTask> tasks;

    @Override
    public void onEnable() {
        this.tasks = ConcurrentHashMap.newKeySet();

        this.saveDefaultConfig();

        ConfigurationSection worlds = this.getConfig().getConfigurationSection("worlds");
        if (worlds == null)
            worlds = this.getConfig().createSection("worlds");

        for (World world : this.getServer().getWorlds()) {
            ConfigurationSection section = worlds.getConfigurationSection(world.getName());
            if (section == null)
                section = worlds.createSection(world.getName());

            double factorDay = section.getDouble("day");
            if (factorDay <= 0.0) {
                factorDay = 1.0;
                section.set("day", factorDay);
            }

            double factorNight = section.getDouble("night");
            if (factorNight <= 0.0) {
                factorNight = 1.0;
                section.set("night", factorNight);
            }

            // ignore this world if it has default behaviour
            if (factorDay == 1.0 && factorNight == 1.0)
                continue;

            this.tasks.add(new WorldTask(world, factorDay, factorNight));
        }

        for (WorldTask task : this.tasks) {
            task.runTaskTimer(this, 1, 1);
        }

        this.saveConfig();
    }

    @Override
    public void onDisable() {
        for (WorldTask task : this.tasks) {
            try {
                task.cancel();
            } catch (IllegalStateException e) {
                this.getLogger().log(Level.WARNING, "Could not cancel task for world \"" + task.getWorldName() + "\" because it was not scheduled yet");
            }
        }

        this.tasks.clear();
        this.tasks = null;
    }
}
