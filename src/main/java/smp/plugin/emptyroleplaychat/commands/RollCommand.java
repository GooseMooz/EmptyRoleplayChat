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

public class RollCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        int dice;

        if (args.length == 0) {
            dice = 6;
        } else if (args.length == 1) {
            try {
                dice = Integer.parseInt(args[0]);
            } catch (NumberFormatException err) {
                player.sendMessage(Component.text("#слишком большое число#")
                        .color(TextColor.color(164, 164, 164))
                        .decorate(TextDecoration.ITALIC));
                return false;
            }
        } else {
            return false;
        }

        Random rand = new Random();
        if (dice <= 0) {
            player.sendMessage(Component.text("#число должно быть больше 0#")
                    .color(TextColor.color(164, 164, 164))
                    .decorate(TextDecoration.ITALIC));
            return false;
        }
        int result = rand.nextInt(dice) + 1;
        Component result_text = Component.text(player.getName()).color(TextColor.color(255, 255, 255))
                .append(Component.text(" бросает D").color(TextColor.color(255, 255, 255)))
                .append(Component.text(dice).color(TextColor.color(255, 255, 255)))
                .append(Component.text(" - [").color(TextColor.color(255, 255, 255)))
                .append(Component.text(result).color(TextColor.color(210, 0, 30)).decorate(TextDecoration.BOLD))
                .append(Component.text("]").color(TextColor.color(255, 255, 255)));


        Component star = Component.text("*").color(TextColor.color(255, 184, 76));
        Component final_message = star.append(result_text).append(star);

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
