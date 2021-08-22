package net.pl3x.map.paper.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.pl3x.map.api.command.Sender;
import net.pl3x.map.paper.command.commands.HideCommand;
import net.pl3x.map.paper.command.commands.ShowCommand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;

import static net.minecraft.commands.Commands.literal;

public class Pl3xMapCommand {
    public Pl3xMapCommand() {
        // TODO figure out how to properly register a brigadier command so bukkit doesn't fuck it all up
        ((CraftServer) Bukkit.getServer()).getHandle().getServer().getCommands().getDispatcher()
                .register(literal("map")
                        .requires(source -> source.hasPermission(2, "pl3xmap.command.map"))
                        .then(HideCommand.register())
                        .then(ShowCommand.register())
                        .executes(this::showHelp)
                );
    }

    private int showHelp(CommandContext<CommandSourceStack> ctx) {
        Sender sender = new PaperSender(ctx.getSource().getBukkitSender());
        // todo
        sender.sendMessage("[Pl3xMap] help");
        return 1;
    }
}
