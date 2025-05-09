package cn.mmf.slashblade_addon.blades;

import cn.mcmod.sakura.block.BlockLoader;
import cn.mcmod.sakura.item.ItemLoader;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeBamboo {
	public static final String namekatana = "wrap.BambooMod.katana";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namekatana);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 45);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F);
            ItemSlashBlade.TextureName.set(tag,"BambooKatana");
	        SlashBlade.registerCustomItemStack(namekatana, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namekatana);
	}

	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);

	    SlashBlade.addRecipe(namekatana, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namekatana), SlashBlade.getCustomBlade(namekatana), SlashBlade.getCustomBlade("slashbladeWrapper"),
				"  S",
				" W ",
				"B  ",
                'S', soul,
                'B', ItemLoader.SAKURAKATANA,
                'W', SlashBlade.findItemStack(SlashBlade.modid,"slashbladeWrapper",1)));
	}
	@SubscribeEvent
	public void InitFoxRecipes(PostInitEvent event){
	    String whiteFoxBladeName = "flammpfeil.slashblade.named.fox.white";
	    
	    ItemStack whiteFoxBladeMaterial =SlashBlade.getCustomBlade(namekatana);
	    whiteFoxBladeMaterial.addEnchantment(Enchantments.LOOTING,1);
	    NBTTagCompound reqTag1 = ItemSlashBlade.getItemTagCompound(whiteFoxBladeMaterial);
        ItemSlashBlade.KillCount.set(reqTag1,199);
        ItemSlashBlade.ProudSoul.set(reqTag1,1000);
        ItemSlashBlade.RepairCount.set(reqTag1,1);
	    ItemStack whiteFoxBlade = SlashBlade.findItemStack(SlashBlade.modid,whiteFoxBladeName, 1);

	    ItemStack wheat = (Loader.isModLoaded("tofucraft"))
	    		?SlashBlade.findItemStack("tofucraft","foodset", 1):new ItemStack(Items.WHEAT,1);
	    		if((Loader.isModLoaded("tofucraft")))wheat.setItemDamage(10);
	    SlashBlade.addRecipe(whiteFoxBladeName, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,whiteFoxBladeName),whiteFoxBlade, whiteFoxBladeMaterial,
                "DAD",
				"DBD",
				"DHD",
                'H', wheat,
                'A', SlashBlade.findItemStack(SlashBlade.modid,SlashBlade.ProudSoulStr,1),
                'B', whiteFoxBladeMaterial,
                'D', BlockLoader.KITUNEBI
				)
		);

		String nameBlack = "flammpfeil.slashblade.named.fox.black";
	    ItemStack blackFoxBladeMaterial =SlashBlade.getCustomBlade(namekatana);
	    blackFoxBladeMaterial.addEnchantment(Enchantments.SMITE,1);
	    NBTTagCompound reqTag2 = ItemSlashBlade.getItemTagCompound(blackFoxBladeMaterial);
        ItemSlashBlade.KillCount.set(reqTag2,199);
        ItemSlashBlade.ProudSoul.set(reqTag2,1000);
        ItemSlashBlade.RepairCount.set(reqTag2,1);
	    ItemStack blackFoxBlade = SlashBlade.findItemStack(SlashBlade.modid,"flammpfeil.slashblade.named.fox.black", 1);
	    SlashBlade.addRecipe(nameBlack, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,nameBlack),blackFoxBlade, blackFoxBladeMaterial,
                "DAD",
				"DBD",
				"DHD",
                'H', wheat,
                'A', SlashBlade.findItemStack(SlashBlade.modid,SlashBlade.ProudSoulStr,1),
                'B', blackFoxBladeMaterial,
                'D', BlockLoader.KITUNEBI)
		);
	}
}
