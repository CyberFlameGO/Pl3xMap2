package net.pl3x.map.fabric.client.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.pl3x.map.fabric.client.gui.screen.OptionsScreen;

public class ModMenu implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return OptionsScreen::new;
    }
}
