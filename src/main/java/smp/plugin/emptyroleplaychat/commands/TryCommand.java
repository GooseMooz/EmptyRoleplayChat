package smp.plugin.emptyroleplaychat.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class TryCommand implements CommandExecutor {
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

        Random rand = new Random();
        boolean result = rand.nextBoolean();
        Component result_text;
        if (result) {
            result_text = Component.text("V").color(
                    TextColor.color(0, 230, 100)).decorate(TextDecoration.BOLD);
        } else {
            result_text = Component.text("X").color(
                    TextColor.color(230, 0, 100)).decorate(TextDecoration.BOLD);
        }

        Component msg_compiled = Component.text(player.getName()).append(
                Component.text(msg).decorate(TextDecoration.ITALIC)).color(TextColor.color(255, 255, 255)).append(
                        Component.text(" [").append(result_text).append(Component.text("]"))
        );

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
