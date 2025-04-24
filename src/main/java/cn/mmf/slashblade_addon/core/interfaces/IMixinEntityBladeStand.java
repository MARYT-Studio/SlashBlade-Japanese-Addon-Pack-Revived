package cn.mmf.slashblade_addon.core.interfaces;

import net.minecraft.item.ItemStack;

public interface IMixinEntityBladeStand {
    ItemStack sjap$getOriginalFenceItem();
    void sjap$setOriginalFenceItem(ItemStack stack);
}
