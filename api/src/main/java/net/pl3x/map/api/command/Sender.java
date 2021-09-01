package net.pl3x.map.api.command;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;
import net.pl3x.map.api.Pl3xMap;

public interface Sender {
    Audience getAudience();

    default void sendMessage(String message) {
        sendMessage(MiniMessage.get().parse(Pl3xMap.api().getCommandPrefix() + message));
    }

    default void sendMessage(String message, Template... templates) {
        sendMessage(MiniMessage.get().parse(Pl3xMap.api().getCommandPrefix() + message, templates));
    }

    default void sendMessage(Component component) {
        getAudience().sendMessage(component);
    }
}
