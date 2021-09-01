package net.pl3x.map.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.kyori.adventure.platform.fabric.FabricServerAudiences;
import net.pl3x.map.api.Pl3xMap;
import net.pl3x.map.api.player.PlayerManager;
import net.pl3x.map.api.world.WorldManager;
import net.pl3x.map.core.configuration.Lang;
import net.pl3x.map.fabric.server.command.Pl3xMapCommand;
import net.pl3x.map.fabric.server.player.FabricPlayerManager;
import net.pl3x.map.fabric.server.world.FabricWorldManager;

public class Pl3xMapFabric implements ModInitializer, Pl3xMap {
    public static Pl3xMapFabric INSTANCE;

    private final PlayerManager playerManager;
    private final WorldManager worldManager;

    private FabricServerAudiences audience;

    public Pl3xMapFabric() {
        INSTANCE = this;

        Pl3xMap.Instance.register(this);

        this.playerManager = new FabricPlayerManager();
        this.worldManager = new FabricWorldManager();
    }

    @Override
    public void onInitialize() {
        // register server side audiences
        ServerLifecycleEvents.SERVER_STARTING.register(server -> this.audience = FabricServerAudiences.of(server));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> this.audience = null);

        // register server side command
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> new Pl3xMapCommand(dispatcher));
    }

    @Override
    public String getCommandPrefix() {
        return Lang.COMMAND_PREFIX;
    }

    @Override
    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    @Override
    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    public FabricServerAudiences getAudiences() {
        if (this.audience == null) {
            throw new IllegalStateException("Tried to access Adventure without a running server!");
        }
        return this.audience;
    }
}
