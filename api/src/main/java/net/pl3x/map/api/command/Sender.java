package net.pl3x.map.api.command;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;

public interface Sender {
    Audience getAudience();

    default void sendMessage(String message) {
        sendMessage(MiniMessage.get().parse(message));
    }

    default void sendMessage(String message, Template... templates) {
        sendMessage(MiniMessage.get().parse(message, templates));
    }

    default void sendMessage(Component component) {
        getAudience().sendMessage(component);
    }
}
