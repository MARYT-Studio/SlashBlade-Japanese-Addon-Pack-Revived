package cn.mmf.slashblade_addon.mixins.dupe_blade_fixes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by Furia on 15/05/15.
 */
public class MessageTossDirtyBlade implements IMessage {

    public boolean isDirty = false;
    public MessageTossDirtyBlade() {}
    public MessageTossDirtyBlade(boolean isDirty){
        this.isDirty = isDirty;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.isDirty = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.isDirty);
    }
}
