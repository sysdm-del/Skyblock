package me.sysdm.net.Items.DragonSword;

import me.sysdm.net.CustomCollections.SkyblockList;
import me.sysdm.net.Items.DragonSword.Exceptions.DuplicateLoreException;
import me.sysdm.net.Items.Item;
import me.sysdm.net.Items.ItemTier;
import me.sysdm.net.Skyblock;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class DragonSword extends Item implements Listener, ItemInterface {

    private Material material;

    private ItemTier tier;

    private DragonEnum dragonLevel;

    private int damage;

    private final ItemMeta itemMeta = this.getItemMeta();

    private final SkyblockList<String> loreList = new SkyblockList<>();

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setName(String name) {
        itemMeta.setDisplayName(name);
    }

    public void addLore(String lore) throws DuplicateLoreException {
        if(loreList.contains(lore)) {
            throw new DuplicateLoreException();
        }else{
            loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
            itemMeta.setLore(loreList);
            this.setItemMeta(itemMeta);
        }
    }

    public void setTier(ItemTier tier) {
        this.tier = tier;
    }

    public void setDragonLevel(DragonEnum dragonEnum) {
        this.dragonLevel = dragonEnum;
    }

    public void setDamageAccordingToLevel() {
        switch (dragonLevel) {
            case LOW_DAMAGE:
                this.damage = 9;
                break;
            case MED_DAMAGE:
                this.damage = 20;
                break;
            case HIGH_DAMAGE:
                this.damage = 50;
                break;
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    @Override
    public int size() {
        return this.getAmount();
    }

    @Override
    public String getName() {
        return this.getItemMeta().getDisplayName();
    }

    @Override
    public List<String> getLore() {
        return this.getItemMeta().getLore();
    }

    @Override
    public void setup() {
        setMaterial(Material.DIAMOND_SWORD);
        setDragonLevel(DragonEnum.LOW_DAMAGE);
        setTier(ItemTier.COMMON);
        setDamageAccordingToLevel();

        try {
            setName("&dDragon Sword");
            addLore("&cTier: " + ItemTier.toString(this.tier));
            addLore("&cDamage: " + DragonEnum.toString(this.dragonLevel));
        } catch (DuplicateLoreException e) {
            Skyblock.getInstance().getLogger().warning("Item's failed to setup! Reason: Duplicate lore");
        }
    }

    @Override
    public Material getMaterial() {
        return this.material;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        Player damager = (Player) e.getDamager();
        if(damager.getItemOnCursor().getItemMeta() == itemMeta) {
            e.setDamage(damage);
        }
    }
}
