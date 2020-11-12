package me.sysdm.net.Items.DragonSword;

import org.bukkit.ChatColor;

public enum DragonEnum {

    LOW_DAMAGE, MED_DAMAGE, HIGH_DAMAGE;

    public static String toString(DragonEnum dragonEnum) {
        switch (dragonEnum) {
            case LOW_DAMAGE:
                return ChatColor.GRAY + "9";
            case MED_DAMAGE:
                return ChatColor.AQUA + "20";
            case HIGH_DAMAGE:
                return ChatColor.RED + "50";
        }
        return null;
    }
}
