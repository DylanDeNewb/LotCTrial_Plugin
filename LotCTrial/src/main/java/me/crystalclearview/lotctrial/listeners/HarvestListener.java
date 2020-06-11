package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.HarvestAmount;
import me.crystalclearview.lotctrial.LotCTrial;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;

import java.util.Random;

public class HarvestListener implements Listener {

    public LotCTrial plugin;
    public HarvestListener(LotCTrial plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onCropBreak(BlockBreakEvent e){

        //Setting Variables For Easier Access.
        ItemStack wheat = new ItemStack(Material.WHEAT);
        ItemStack potato = new ItemStack(Material.POTATOES);
        ItemStack carrot = new ItemStack(Material.CARROTS);
        ItemStack beetroot = new ItemStack(Material.BEETROOTS);
        Player p = e.getPlayer();
        Block b = e.getBlock();
        Location location = b.getLocation();
        ItemStack tool = p.getInventory().getItemInMainHand();
        int dropamount = 0;
        ItemStack dropcrop = new ItemStack(Material.AIR);
        ItemStack dropseeds = new ItemStack(Material.AIR);

        if(!(b.getType() == Material.WHEAT) || b.getType() == Material.BEETROOTS || b.getType() == Material.CARROTS || b.getType() == Material.POTATOES){
            return;
        }

        if(b.getState().getData() instanceof Crops && (!(((Crops) b.getState().getData()).getState() == CropState.RIPE))){
            p.sendMessage(ChatColor.RED + "The " + ChatColor.GOLD + b.getType().toString().toLowerCase() + ChatColor.RED + " is not ready to be harvested! Be patient!");
            return;
        }

        if(b.getType() == Material.WHEAT ){
            dropcrop.setType(Material.WHEAT);
            dropseeds.setType(Material.WHEAT_SEEDS);
        }
        else if(b.getType() == Material.POTATOES){
            dropcrop.setType(Material.POTATO);
            dropseeds.setType(Material.POTATO);
        }
        else if(b.getType() == Material.CARROTS){
            dropcrop.setType(Material.CARROT);
            dropseeds.setType(Material.CARROT);
        }
        else if(b.getType() == Material.BEETROOTS){
            dropcrop.setType(Material.BEETROOT);
            dropseeds.setType(Material.BEETROOT_SEEDS);
        }

        if(tool.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
            int level = tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            dropamount = level;
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
                ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + 1);
            }
            tool.setItemMeta(meta);

            p.sendMessage(ChatColor.GREEN + "You recieved " + ChatColor.GOLD + dropcrop.getAmount() + " produce and " + dropseeds.getAmount() + " seeds!");
        }
        else if(tool.getType() == Material.IRON_HOE || tool.getType() == Material.GOLDEN_HOE){

            Random rand = new Random();
            int seedvalue = rand.nextInt(3);
            if(seedvalue == 0){
                seedvalue++;
            }
            HarvestAmount.irongoldhoe.setSeed(seedvalue);

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

            if(meta.getDisplayName().equals(ChatColor.GREEN + "Lucky Hoe")) {
                Random random = new Random();
                double chance = random.nextDouble();
                if (chance <= 0.25) {
                    p.sendMessage(ChatColor.GREEN + "You recieved " + ChatColor.GOLD + "2 produce" + ChatColor.GREEN + " as a result of your lucky hoe!");
                    p.getInventory().addItem(new ItemStack(Material.WHEAT, 2));
                }
            }
        }
        else if(tool.getType() == Material.DIAMOND_HOE){

            Random rand = new Random();
            int seedvalue = rand.nextInt(4);
            if(seedvalue == 0){
                seedvalue++;
            }
            HarvestAmount.diamondhoe.setSeed(seedvalue);

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
        }else {
            //If crop is broke with your fist It will drop nothing and send a message.
            if(b.getType() == Material.WHEAT || b.getType() == Material.BEETROOTS || b.getType() == Material.CARROTS || b.getType() == Material.POTATOES || b.getType() == Material.WHEAT_SEEDS || b.getType() == Material.BEETROOT_SEEDS || b.getType() == Material.CARROT || b.getType() == Material.POTATO){
                e.setCancelled(true); //Stops wheat and seeds being given to the player
                p.sendMessage(ChatColor.RED + "The " + ChatColor.GOLD + b.getType().toString().toLowerCase() + ChatColor.RED + " withers and you are left with no produce! Use a hoe!");
                b.setType(Material.AIR);
            }
        }



    }}


