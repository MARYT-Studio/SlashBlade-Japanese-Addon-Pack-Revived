package cn.mmf.slashblade_addon.core.interfaces;

import cn.mmf.slashblade_addon.network.MessageTossDirtyBlade;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IMixinCoreProxy {
    IMessage sjap$onMessage(MessageTossDirtyBlade message, MessageContext ctx);
}
