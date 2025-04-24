package cn.mmf.slashblade_addon.mixins.interfaces;

import net.minecraft.item.ItemStack;

public interface IMixinEntityBladeStand {
    ItemStack sjap$getOriginalFenceItem();
    void sjap$setOriginalFenceItem(ItemStack stack);
}
