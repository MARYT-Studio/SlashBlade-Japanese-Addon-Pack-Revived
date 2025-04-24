package cn.mmf.slashblade_addon.mixins.init;

import static cn.mmf.slashblade_addon.SJAP.MOD_ID;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class MixinInit implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins." + MOD_ID + ".json");
    }
}
