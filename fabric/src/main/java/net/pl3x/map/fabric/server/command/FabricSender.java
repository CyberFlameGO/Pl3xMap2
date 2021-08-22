package net.pl3x.map.fabric.server.command;

import net.kyori.adventure.audience.Audience;
import net.minecraft.server.command.ServerCommandSource;
import net.pl3x.map.api.command.Sender;
import net.pl3x.map.fabric.Pl3xMapFabric;

public class FabricSender implements Sender {
    private final ServerCommandSource sender;
    private final Audience audience;

    public FabricSender(ServerCommandSource sender) {
        this.sender = sender;
        this.audience = Pl3xMapFabric.INSTANCE.getAudiences().audience(this.sender);
    }

    public ServerCommandSource getSender() {
        return this.sender;
    }

    @Override
    public Audience getAudience() {
        return this.audience;
    }
}
