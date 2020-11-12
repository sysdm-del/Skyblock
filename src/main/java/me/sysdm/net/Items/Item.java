package me.sysdm.net.Items;

import me.sysdm.net.Items.DragonSword.ItemInterface;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Item extends ItemStack implements ItemInterface {

    public abstract int size();

    public abstract String getName();

    public abstract List<String> getLore();

    public abstract Material getMaterial();


}
