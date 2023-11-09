package smp.plugin.emptyroleplaychat.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Write something to do!");
            return true;
        }

        String msg = "";

        for (String arg : args) {
            msg = msg.concat(" ").concat(arg);
        }

        Component msg_compiled = Component.text(player.getName()).append(
                Component.text(msg).decorate(TextDecoration.ITALIC)).color(TextColor.color(255, 255, 255));

        Component star = Component.text("*").color(TextColor.color(255, 184, 76));
        Component final_message = star.append(msg_compiled).append(star);

        List<Entity> nearbyPlayers = player.getNearbyEntities(100, 100, 100);

        for (Entity entity : nearbyPlayers) {
            if (entity instanceof Player nearbyPlayer) {
                nearbyPlayer.sendMessage(final_message);
            }
        }

        player.sendMessage(final_message);
        return true;
    }
}
