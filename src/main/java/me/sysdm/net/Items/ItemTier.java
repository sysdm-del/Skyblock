package me.sysdm.net.Items;

import me.sysdm.net.Items.DragonSword.DragonEnum;
import org.bukkit.ChatColor;

public enum ItemTier {

    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY;

    public static String toString(ItemTier itemTier) {
        switch (itemTier) {
            case COMMON:
                return ChatColor.GRAY + "COMMON";
            case UNCOMMON:
                return ChatColor.GREEN + "UNCOMMON";
            case RARE:
                return ChatColor.AQUA + "RARE";
            case EPIC:
                return ChatColor.DARK_PURPLE + "EPIC";
            case LEGENDARY:
                return ChatColor.GOLD + "LEGENDARY";
        }
        return null;
    }

}
