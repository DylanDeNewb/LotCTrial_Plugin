package me.crystalclearview.lotctrial.Events;

import me.crystalclearview.lotctrial.HarvestAmount;
import me.crystalclearview.lotctrial.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class HarvestEvent implements Listener {

    public Main plugin;
    public HarvestEvent(Main plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onCropBreak(BlockBreakEvent e){

        //Setting Variables For Easier Access.
        Player p = e.getPlayer();
        Block b = e.getBlock();
        Location location = b.getLocation();
        ItemStack tool = p.getInventory().getItemInMainHand();
        int dropamount = 0;
        ItemStack dropcrop = new ItemStack(Material.AIR);
        ItemStack dropseeds = new ItemStack(Material.AIR);

        //Setting Drop Item
        if(b.getType() == Material.WHEAT ){
            dropcrop.setType(Material.WHEAT);
            dropseeds.setType(Material.WHEAT_SEEDS);
        }
        if(b.getType() == Material.POTATOES){
            dropcrop.setType(Material.POTATO);
            dropseeds.setType(Material.POTATO);
        }
        if(b.getType() == Material.CARROTS){
            dropcrop.setType(Material.CARROT);
            dropseeds.setType(Material.CARROT);
        }
        if(b.getType() == Material.BEETROOTS){
            dropcrop.setType(Material.BEETROOT);
            dropseeds.setType(Material.BEETROOT_SEEDS);
        }

        //If block broken equals a crop it will now run certain actions
        if(b.getType() == Material.WHEAT || b.getType() == Material.POTATOES || b.getType() == Material.CARROTS || b.getType() == Material.BEETROOTS){

            if(tool.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
                int level = tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                dropamount = level;
            }

            //If crop is broke with your fist It will drop nothing and send a message.
            if(tool.getType() == Material.AIR){
                e.setCancelled(true); //Stops wheat and seeds being given to the player
                p.sendMessage(ChatColor.RED + "The " + ChatColor.GOLD + b.getType() + ChatColor.RED + " withers and you are left with no produce! Use a hoe!");
                b.setType(Material.AIR);
            }
            //Dropping seed and crop + Fortune bonus for breaking with a wooden hoe or stone hoe,
            else if(tool.getType() == Material.WOODEN_HOE || tool.getType() == Material.STONE_HOE){
                e.setCancelled(true);
                dropcrop.setAmount(dropamount + HarvestAmount.stonewoodhoe.getCrop()); //Using enums from "HarvestAmount"
                dropseeds.setAmount(dropamount + HarvestAmount.stonewoodhoe.getSeed()); //Using enums from "HarvestAmount"
                b.getWorld().dropItemNaturally(location, dropcrop);
                b.getWorld().dropItemNaturally(location, dropseeds);
                b.setType(Material.AIR);
                ItemMeta meta = tool.getItemMeta();
                if (meta instanceof Damageable){
                    ((Damageable) meta).setDamage(((Damageable) meta).getDamage() - 1);
                }
                tool.setItemMeta(meta);

                p.sendMessage(ChatColor.GREEN + "You recieved " + ChatColor.GOLD + dropcrop.getAmount() + " produce and " + dropseeds.getAmount() + " seeds!");
            }
            else if(tool.getType() == Material.IRON_HOE || tool.getType() == Material.GOLDEN_HOE){
                e.setCancelled(true);
                dropcrop.setAmount(dropamount + HarvestAmount.irongoldhoe.getCrop()); //Using enums from "HarvestAmount"
                dropseeds.setAmount(dropamount + HarvestAmount.irongoldhoe.getSeed()); //Using enums from "HarvestAmount"
                b.getWorld().dropItemNaturally(location, dropcrop);
                b.getWorld().dropItemNaturally(location, dropseeds);
                b.setType(Material.AIR);
                ItemMeta meta = tool.getItemMeta();
                if (meta instanceof Damageable){
                    ((Damageable) meta).setDamage(((Damageable) meta).getDamage() - 1);
                }
                tool.setItemMeta(meta);

                p.sendMessage(ChatColor.GREEN + "You recieved " + ChatColor.GOLD + dropcrop.getAmount() + " produce and " + dropseeds.getAmount() + " seeds!");

                if(meta.getDisplayName().equals(ChatColor.GREEN + "Lucky Hoe"));
                    Random random = new Random();
                    double chance = random.nextDouble();
                    if(chance <= 0.25) {
                        p.sendMessage(ChatColor.GREEN + "You recieved " + ChatColor.GOLD + "2 produce" + ChatColor.GREEN + " as a result of your lucky hoe!");
                        p.getInventory().addItem(new ItemStack(Material.WHEAT, 2));
                    }
                }
            }
            else if(tool.getType() == Material.DIAMOND_HOE){
                e.setCancelled(true);
                dropcrop.setAmount(dropamount + HarvestAmount.diamondhoe.getCrop()); //Using enums from "HarvestAmount"
                dropseeds.setAmount(dropamount + HarvestAmount.diamondhoe.getSeed()); //Using enums from "HarvestAmount"
                b.getWorld().dropItemNaturally(location, dropcrop);
                b.getWorld().dropItemNaturally(location, dropseeds);
                b.setType(Material.AIR);
                ItemMeta meta = tool.getItemMeta();
                if (meta instanceof Damageable){
                    ((Damageable) meta).setDamage(((Damageable) meta).getDamage() - 1);
                }
                tool.setItemMeta(meta);

                p.sendMessage(ChatColor.GREEN + "You recieved " + ChatColor.GOLD + dropcrop.getAmount() + " produce and " + dropseeds.getAmount() + " seeds!");
            }


        }


    }


