package net.pl3x.map.core.configuration;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AbstractConfig {
    public void reload(File dir, String file, Class<? extends AbstractConfig> clazz) {
        // todo
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface Key {
        String value();
    }
}
