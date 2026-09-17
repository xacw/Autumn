package dev.xac.autumn.commands.admin.general;

import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.Autumn;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = { "setspawn" }, permission = "praxi.setspawn")
public class SetSpawnCommand
{
    public void execute(Player player) {
        Autumn.get().getEssentials().setSpawn(player.getLocation());
        player.sendMessage(CC.translate("&bSpawn set successfully!"));
    }
}
