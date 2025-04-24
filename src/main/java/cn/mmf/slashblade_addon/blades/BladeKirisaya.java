package cn.mmf.slashblade_addon.blades;

import cn.mmf.slashblade_addon.recipes.RecipeKirisaya;
import cn.mmf.slashblade_addon.specialeffect.SELoader;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import static cn.mmf.slashblade_addon.ConfigLoader.nerf_Kirisaya;

public class BladeKirisaya {
	public static final String name = "flammpfeil.slashblade.named.kirisaya";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.POWER, 8);
	        customblade.addEnchantment(Enchantments.UNBREAKING, 10);
	        customblade.addEnchantment(Enchantments.INFINITY, 4);
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 11);
	        ItemSlashBlade.setBaseAttackModifier(tag, 3.0F);
	        ItemSlashBlade.TextureName.set(tag, "named/kirisaya/kirisaya");
	        ItemSlashBlade.ModelName.set(tag, "named/kirisaya/kirisaya");
	        ItemSlashBlade.SpecialAttackType.set(tag, 6);
	        ItemSlashBlade.StandbyRenderType.set(tag, 1);
	        SpecialEffects.addEffect(customblade, SELoader.BurstDrive);
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	    ItemSlashBlade.SpecialAttackType.set(sphere.getTagCompound(), 0);
	    ItemStack blade = SlashBlade.getCustomBlade(name);
	    ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
	    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
	    ItemSlashBlade.RepairCount.set(tag, 1);
	    ItemSlashBlade.KillCount.set(tag, 1000);
	    ItemSlashBlade.ProudSoul.set(tag, nerf_Kirisaya ? 10000 : 20000);
	    reqiredBlade.addEnchantment(Enchantments.SHARPNESS, 3);
	    reqiredBlade.addEnchantment(Enchantments.POWER, 3);
	    
	    SlashBlade.addRecipe(name, new RecipeKirisaya(new ResourceLocation("flammpfeil.slashblade",name), blade, reqiredBlade, sphere,
				"DGD",
                "ZBZ",
                "GDG",
                'G', new ItemStack(Items.GOLDEN_APPLE, 1, (nerf_Kirisaya ? 0 : 1)),
                'D', new ItemStack((nerf_Kirisaya ? Items.BOOK : Items.RECORD_11)),
                'B', reqiredBlade,
                'Z', sphere));
	    
	}
}
