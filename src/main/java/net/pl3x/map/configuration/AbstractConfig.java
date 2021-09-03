package net.pl3x.map.configuration;

import net.pl3x.map.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

public class AbstractConfig {
    private YamlConfiguration config;

    public void reload(File dir, String file, Class<? extends AbstractConfig> clazz) {
        File configFile = new File(dir, file);
        this.config = new YamlConfiguration();
        try {
            this.config.load(configFile);
        } catch (IOException ignore) {
        } catch (InvalidConfigurationException e) {
            Logger.severe("Could not load " + file + ", please correct your syntax errors", e);
            throw new RuntimeException(e);
        }
        this.config.options().copyDefaults(true);

        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            final Key key = field.getDeclaredAnnotation(Key.class);
            if (key != null) {
                try {
                    field.set(null, this.config.get(key.value(), field.get(null)));
                } catch (IllegalAccessException e) {
                    Logger.warn("Failed to load " + file, e);
                }
            }
        });

        try {
            this.config.save(configFile);
        } catch (IOException e) {
            Logger.severe("Could not save " + file, e);
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface Key {
        String value();
    }
}
