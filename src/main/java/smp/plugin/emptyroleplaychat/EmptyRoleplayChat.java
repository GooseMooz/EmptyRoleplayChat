package smp.plugin.emptyroleplaychat;

import org.bukkit.plugin.java.JavaPlugin;
import smp.plugin.emptyroleplaychat.commands.MeCommand;
import smp.plugin.emptyroleplaychat.commands.RollCommand;
import smp.plugin.emptyroleplaychat.commands.TryCommand;

public final class EmptyRoleplayChat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("me").setExecutor(new MeCommand());
        getCommand("try").setExecutor(new TryCommand());
        getCommand("roll").setExecutor(new RollCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
