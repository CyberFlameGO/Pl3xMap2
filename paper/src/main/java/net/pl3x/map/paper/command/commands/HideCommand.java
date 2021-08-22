package net.pl3x.map.paper.command.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.kyori.adventure.text.minimessage.Template;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.pl3x.map.api.Pl3xMap;
import net.pl3x.map.api.command.Sender;
import net.pl3x.map.api.player.Player;
import net.pl3x.map.paper.command.PaperSender;
import net.pl3x.map.core.configuration.Lang;

import java.util.Collection;
import java.util.Collections;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.arguments.EntityArgument.getPlayers;

public class HideCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return literal("hide")
                .requires(source -> source.hasPermission(2, "pl3xmap.command.hide"))
                .then(argument("player", EntityArgument.players())
                        .requires(source -> source.hasPermission(2, "pl3xmap.command.hide.other"))
                        .executes(ctx -> hide(ctx, getPlayers(ctx, "player")))
                )
                .executes(ctx -> hide(ctx, Collections.singleton(ctx.getSource().getPlayerOrException())));
    }

    private static int hide(CommandContext<CommandSourceStack> ctx, Collection<ServerPlayer> players) {
        int i = 0;
        Sender sender = new PaperSender(ctx.getSource().getBukkitSender());
        for (ServerPlayer serverPlayer : players) {
            Player player = Pl3xMap.api().getPlayerManager().getPlayer(serverPlayer.getUUID());
            if (player.isHidden()) {
                sender.sendMessage(Lang.PLAYER_ALREADY_HIDDEN, Template.of("player", player.getName()));
                continue;
            }
            player.setHidden(true, true);
            sender.sendMessage(Lang.HIDE_PLAYER_SUCCESS, Template.of("player", player.getName()));
            i++;
        }
        return i;
    }
}
