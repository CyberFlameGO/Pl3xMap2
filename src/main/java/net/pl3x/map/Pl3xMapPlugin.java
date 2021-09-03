package net.pl3x.map;

import net.pl3x.map.command.Pl3xMapCommand;
import net.pl3x.map.configuration.Config;
import net.pl3x.map.configuration.Lang;
import net.pl3x.map.player.PlayerManager;
import net.pl3x.map.util.FileUtil;
import net.pl3x.map.world.WorldManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public class Pl3xMapPlugin extends JavaPlugin implements Pl3xMap {
    private static Pl3xMapPlugin INSTANCE;

    public static Pl3xMapPlugin getInstance() {
        return INSTANCE;
    }

    private final PlayerManager playerManager;
    private final WorldManager worldManager;

    public Pl3xMapPlugin() {
        INSTANCE = this;

        try {
            // try to hack in a fancier logger :3
            Field logger = JavaPlugin.class.getDeclaredField("logger");
            logger.setAccessible(true);
            logger.set(this, Logger.LOGGER);
        } catch (NoSuchFieldException | IllegalAccessException ignore) {
        }

        this.playerManager = new PlayerManager(this);
        this.worldManager = new WorldManager();
    }

    @Override
    public void onEnable() {
        reload();

        // https://bstats.org/plugin/bukkit/Pl3xMap/10133
        new Metrics(this, 10133);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void reload() {
        // disable everything
        onDisable();

        // load up configs
        Config.reload(getDataFolder());

        // this has to load after config.yml in order to know if web dir should be overwritten
        // but also before advanced.yml to ensure foliage.png and grass.png are already on disk
        FileUtil.extract("/web/", FileUtil.WEB_DIR.toFile(), Config.UPDATE_WEB_DIR);
        FileUtil.extract("/locale/", FileUtil.LOCALE_DIR.toFile(), false);

        // load language file
        Lang.reload(new File(getDataFolder(), "locale"));

        // register command executor
        PluginCommand command = getCommand("map");
        if (command != null) {
            command.setExecutor(new Pl3xMapCommand(this));
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
}
