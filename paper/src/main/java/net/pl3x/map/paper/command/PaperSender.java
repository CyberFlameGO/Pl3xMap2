package net.pl3x.map.paper.command;

import net.kyori.adventure.audience.Audience;
import net.pl3x.map.api.command.Sender;
import net.pl3x.map.paper.Pl3xMapPaper;
import org.bukkit.command.CommandSender;

public class PaperSender implements Sender {
    private final CommandSender sender;
    private final Audience audience;

    public PaperSender(CommandSender sender) {
        this.sender = sender;
        this.audience = Pl3xMapPaper.INSTANCE.getAudiences().sender(this.sender);
    }

    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public Audience getAudience() {
        return this.audience;
    }
}
