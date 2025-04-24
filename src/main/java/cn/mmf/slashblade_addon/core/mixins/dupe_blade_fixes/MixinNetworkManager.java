package cn.mmf.slashblade_addon.core.mixins.dupe_blade_fixes;

import cn.mmf.slashblade_addon.network.MessageTossDirtyBlade;
import cn.mmf.slashblade_addon.network.MessageTossDirtyBladeHandler;
import mods.flammpfeil.slashblade.network.*;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NetworkManager.class, remap = false)
public class MixinNetworkManager {

    @Shadow
    @Final
    public static SimpleNetworkWrapper INSTANCE;

    @Inject(
            method = "init",
            at = @At("RETURN")
    )
    private static void inject_init(CallbackInfo ci) {
        INSTANCE.registerMessage(MessageTossDirtyBladeHandler.class, MessageTossDirtyBlade.class, 4, Side.CLIENT);
    }
}
