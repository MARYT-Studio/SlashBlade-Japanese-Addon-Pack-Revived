package cn.mmf.slashblade_addon.mixins.dupe_blade_fixes;

import cn.mmf.slashblade_addon.mixins.interfaces.IMixinCoreProxy;
import mods.flammpfeil.slashblade.core.CoreProxy;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = CoreProxy.class, remap = false)
public class MixinCoreProxy implements IMixinCoreProxy {
    @Unique
    public IMessage sjap$onMessage(MessageTossDirtyBlade message, MessageContext ctx) {
        return null;
    }
}
