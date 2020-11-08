package me.sysdm.net.Items;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class Item extends ItemStack {

    public void setName(String s) {
        getItemMeta().setDisplayName(s);
    }
    public void setLore(String s) {
        getItemMeta().setLore(Arrays.asList(s));
    }

    public abstract int size();

    public abstract String getName();

    public abstract List<String> getLore();

}
