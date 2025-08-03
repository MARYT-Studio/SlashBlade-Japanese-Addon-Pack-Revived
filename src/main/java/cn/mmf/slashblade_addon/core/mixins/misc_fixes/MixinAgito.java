package cn.mmf.slashblade_addon.core.mixins.misc_fixes;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.Agito;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.*;

// TODO: Maybe there is a better way to remove the custom display name without pain
// Currently this method does not work on TLS

@Mixin(value = Agito.class, remap = false, priority = 2000)
public abstract class MixinAgito {
    /**
     * @author RisingInIris2017
     * @reason remove the unnecessary display name
     */
    @Overwrite
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack itemProudSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "proudsoul", 1);
        ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
        String nameAgito = "flammpfeil.slashblade.named.agito";
        String nameAgitoRust = nameAgito + ".rust";
        String nameAgitoReqired = nameAgito + ".reqired";

        // Prepare the agito_false (blue)
        ItemStack agitoFalse = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        agitoFalse.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, nameAgito);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.setBaseAttackModifier(tag, 4.0F + Item.ToolMaterial.IRON.getAttackDamage());
        mods.flammpfeil.slashblade.item.ItemSlashBlade.TextureName.set(tag, "named/agito_false");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.ModelName.set(tag, "named/agito");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.SpecialAttackType.set(tag, 2);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.StandbyRenderType.set(tag, 2);
        SlashBlade.registerCustomItemStack(nameAgito, agitoFalse);
        ItemSlashBladeNamed.NamedBlades.add(nameAgito);

        // Prepare the agito rust
        ItemStack agitoRust = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        tag = new NBTTagCompound();
        agitoRust.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, nameAgitoRust);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.setBaseAttackModifier(tag, 4.0F + Item.ToolMaterial.STONE.getAttackDamage());
        mods.flammpfeil.slashblade.item.ItemSlashBlade.TextureName.set(tag, "named/agito_rust");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.ModelName.set(tag, "named/agito");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.SpecialAttackType.set(tag, 2);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.StandbyRenderType.set(tag, 2);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.IsSealed.set(tag, true);
        ItemSlashBladeNamed.TrueItemName.set(tag, nameAgito);
        NamedBladeManager.registerBladeSoul(tag, agitoRust.getDisplayName());
        SlashBlade.registerCustomItemStack(nameAgitoRust, agitoRust);
        ItemSlashBladeNamed.NamedBlades.add(nameAgitoRust);

        // Rust cleaning recipe
        ItemStack agitoRustToClean = agitoRust.copy();
        NBTTagCompound reqTag = mods.flammpfeil.slashblade.item.ItemSlashBlade.getItemTagCompound(agitoRustToClean);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.KillCount.set(reqTag, 100);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.RepairCount.set(reqTag, 1);
//        agitoRustToClean.setStackDisplayName("agito rust");
        SlashBlade.registerCustomItemStack(nameAgitoReqired, agitoRustToClean);
        ItemSlashBladeNamed.NamedBlades.add(nameAgitoReqired);
        ItemStack destBlade = SlashBlade.findItemStack("flammpfeil.slashblade", ItemSlashBladeNamed.TrueItemName.get(tag), 1);
        SlashBlade.addRecipe(nameAgito, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade", "agito"), destBlade, agitoRustToClean, " X ", "XBX", " X ", 'X', itemProudSoul, 'B', agitoRustToClean));

        // -------------------------------------

        // Orotiagito (red)
        nameAgito = "flammpfeil.slashblade.named.orotiagito";
        nameAgitoRust = nameAgito + ".seald";
        nameAgitoReqired = nameAgito + ".reqired";
        String nameOrotiagitoRust = nameAgito + ".rust";
        String nameOrotiagitoSealdReqired = nameAgitoRust + ".reqired";

        // Prepare the sealed Orotiagito
        ItemStack breakableAgito = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        reqTag = new NBTTagCompound();
        breakableAgito.setTagCompound(reqTag);
        ItemSlashBladeNamed.CurrentItemName.set(reqTag, nameAgito);
        ItemSlashBladeNamed.CustomMaxDamage.set(reqTag, 60);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.setBaseAttackModifier(reqTag, 4.0F + Item.ToolMaterial.DIAMOND.getAttackDamage());
        mods.flammpfeil.slashblade.item.ItemSlashBlade.TextureName.set(reqTag, "named/orotiagito");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.ModelName.set(reqTag, "named/agito");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.SpecialAttackType.set(reqTag, 2);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.StandbyRenderType.set(reqTag, 2);
        SlashBlade.registerCustomItemStack(nameAgito, breakableAgito);
        ItemSlashBladeNamed.NamedBlades.add(nameAgito);
        String brokableTest = nameAgito + ".damaged";
        ItemStack brokable = breakableAgito.copy();
        brokable.setItemDamage(brokable.getMaxDamage());
        SlashBlade.registerCustomItemStack(brokableTest, brokable);
        ItemSlashBladeNamed.NamedBlades.add(brokableTest);

        // Prepare the sealed Orotiagito
        ItemStack sealedOrotiAgito = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        reqTag = new NBTTagCompound();
        sealedOrotiAgito.setTagCompound(reqTag);
        ItemSlashBladeNamed.CurrentItemName.set(reqTag, nameAgitoRust);
        ItemSlashBladeNamed.CustomMaxDamage.set(reqTag, 60);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.setBaseAttackModifier(reqTag, 4.0F + Item.ToolMaterial.IRON.getAttackDamage());
        mods.flammpfeil.slashblade.item.ItemSlashBlade.TextureName.set(reqTag, "named/agito_true");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.ModelName.set(reqTag, "named/agito");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.SpecialAttackType.set(reqTag, 2);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.StandbyRenderType.set(reqTag, 2);
        ItemSlashBladeNamed.TrueItemName.set(reqTag, nameAgito);
        SlashBlade.registerCustomItemStack(nameAgitoRust, sealedOrotiAgito);
        ItemSlashBladeNamed.NamedBlades.add(nameAgitoRust);

        // Sealed Orotiagito recipe
        ItemStack reqiredBlade2 = sealedOrotiAgito.copy();
        NBTTagCompound reqTag2 = mods.flammpfeil.slashblade.item.ItemSlashBlade.getItemTagCompound(sealedOrotiAgito);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.KillCount.set(reqTag2, 1000);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.ProudSoul.set(reqTag2, 1000);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.RepairCount.set(reqTag2, 10);
//        sealedOrotiAgito.setStackDisplayName("orotiagito seald");
        SlashBlade.registerCustomItemStack(nameAgitoReqired, reqiredBlade2);
        ItemSlashBladeNamed.NamedBlades.add(nameAgitoReqired);
        ItemStack destBlade2 = SlashBlade.findItemStack("flammpfeil.slashblade", ItemSlashBladeNamed.TrueItemName.get(reqTag), 1);
        SlashBlade.addRecipe(nameAgito, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade", "agito_ex"), destBlade2, reqiredBlade2, "PXP", "XBX", "PXP", 'X', itemSphereBladeSoul, 'P', itemProudSoul, 'B', reqiredBlade2));

        // Prepare the Orotiagito rust cleaning
        ItemStack reqiredBlade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        reqTag = new NBTTagCompound();
        reqiredBlade.setTagCompound(reqTag);
        ItemSlashBladeNamed.CurrentItemName.set(reqTag, nameOrotiagitoRust);
        ItemSlashBladeNamed.CustomMaxDamage.set(reqTag, 60);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.setBaseAttackModifier(reqTag, 4.0F + Item.ToolMaterial.STONE.getAttackDamage());
        mods.flammpfeil.slashblade.item.ItemSlashBlade.TextureName.set(reqTag, "named/agito_rust_true");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.ModelName.set(reqTag, "named/agito");
        mods.flammpfeil.slashblade.item.ItemSlashBlade.SpecialAttackType.set(reqTag, 2);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.StandbyRenderType.set(reqTag, 2);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.IsSealed.set(reqTag, true);
        ItemSlashBladeNamed.TrueItemName.set(reqTag, nameAgitoRust);
        NamedBladeManager.registerBladeSoul(reqTag, reqiredBlade.getDisplayName());
        SlashBlade.registerCustomItemStack(nameOrotiagitoRust, reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add(nameOrotiagitoRust);
        reqiredBlade = reqiredBlade.copy();
        reqTag = mods.flammpfeil.slashblade.item.ItemSlashBlade.getItemTagCompound(reqiredBlade);
        mods.flammpfeil.slashblade.item.ItemSlashBlade.KillCount.set(reqTag, 100);
        ItemSlashBlade.RepairCount.set(reqTag, 1);
//        reqiredBlade.setStackDisplayName("agito rust");
        SlashBlade.registerCustomItemStack(nameOrotiagitoSealdReqired, reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add(nameOrotiagitoSealdReqired);
        destBlade = SlashBlade.findItemStack("flammpfeil.slashblade", ItemSlashBladeNamed.TrueItemName.get(reqTag), 1);
        SlashBlade.addRecipe(nameAgitoRust, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade", "agito2"), destBlade, reqiredBlade, " X ", "XBX", " X ", 'X', itemProudSoul, 'B', reqiredBlade));
    }
}
