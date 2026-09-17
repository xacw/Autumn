package dev.xac.autumn.commands.user.gamer;

import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "suicide")
public class SuicideCommand {
    public void execute(Player player) {
        player.setHealth(0);
        player.sendMessage(CC.translate("&cYou have killed yourself! Oh noes"));
    }
}
