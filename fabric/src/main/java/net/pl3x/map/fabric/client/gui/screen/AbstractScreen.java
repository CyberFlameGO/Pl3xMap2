package net.pl3x.map.fabric.client.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

public class AbstractScreen extends Screen {
    final Screen parent;

    protected AbstractScreen(Screen parent) {
        super(new TranslatableText("pl3xmap.screen.options.title"));
        this.parent = parent;
    }
}
