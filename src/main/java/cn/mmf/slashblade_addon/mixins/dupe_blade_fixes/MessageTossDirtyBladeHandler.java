package cn.mmf.slashblade_addon.mixins.dupe_blade_fixes;

import cn.mmf.slashblade_addon.mixins.interfaces.IMixinCoreProxy;
import mods.flammpfeil.slashblade.core.CoreProxy;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTossDirtyBladeHandler implements IMessageHandler<MessageTossDirtyBlade, IMessage> {

    @Override
    public IMessage onMessage(MessageTossDirtyBlade message, MessageContext ctx) {
        return ((IMixinCoreProxy)CoreProxy.proxy).sjap$onMessage(message, ctx);
    }
}