package cn.mmf.slashblade_addon.mixins.interfaces;

import cn.mmf.slashblade_addon.mixins.dupe_blade_fixes.MessageTossDirtyBlade;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IMixinCoreProxy {
    IMessage sjap$onMessage(MessageTossDirtyBlade message, MessageContext ctx);
}
