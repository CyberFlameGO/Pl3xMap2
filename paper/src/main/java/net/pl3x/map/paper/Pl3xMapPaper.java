package net.pl3x.map.paper;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.pl3x.map.api.Pl3xMap;
import net.pl3x.map.api.player.PlayerManager;
import net.pl3x.map.api.world.WorldManager;
import net.pl3x.map.paper.command.Pl3xMapCommand;
import net.pl3x.map.paper.player.PaperPlayerManager;
import net.pl3x.map.paper.world.PaperWorldManager;
import net.pl3x.map.core.Logger;
import net.pl3x.map.core.configuration.Config;
import net.pl3x.map.core.configuration.Lang;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public class Pl3xMapPaper extends JavaPlugin implements Pl3xMap {
    public static Pl3xMapPaper INSTANCE;

    private final PlayerManager playerManager;
    private final WorldManager worldManager;

    private BukkitAudiences audiences;

    public Pl3xMapPaper() {
        INSTANCE = this;

        Pl3xMap.Instance.register(this);

        try {
            // try to hack in a fancier logger :3
            Field logger = JavaPlugin.class.getDeclaredField("logger");
            logger.setAccessible(true);
            logger.set(this, Logger.LOGGER);
        } catch (NoSuchFieldException | IllegalAccessException ignore) {
        }

        this.playerManager = new PaperPlayerManager();
        this.worldManager = new PaperWorldManager();
    }

    @Override
    public void onEnable() {
        Config.reload(getDataFolder());
        Lang.reload(new File(getDataFolder(), "locale"));

        // register command
        new Pl3xMapCommand();

        // https://bstats.org/plugin/bukkit/Pl3xMap/10133
        new Metrics(this, 10133);
    }

    @Override
    public void onDisable() {
        if (this.audiences != null) {
            this.audiences.close();
            this.audiences = null;
        }
    }

    @Override
    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    @Override
    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    public BukkitAudiences getAudiences() {
        if (this.audiences == null) {
            this.audiences = BukkitAudiences.create(this);
        }
        return audiences;
    }
}
