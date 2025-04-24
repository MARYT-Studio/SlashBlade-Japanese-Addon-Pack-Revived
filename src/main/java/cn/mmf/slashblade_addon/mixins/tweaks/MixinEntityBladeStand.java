package cn.mmf.slashblade_addon.mixins.tweaks;

import cn.mmf.slashblade_addon.mixins.interfaces.IMixinEntityBladeStand;
import mods.flammpfeil.slashblade.entity.EntityBladeStand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;

@Mixin(value = EntityBladeStand.class, remap = false)
public abstract class MixinEntityBladeStand extends Entity implements IMixinEntityBladeStand {
    private MixinEntityBladeStand(World world) {
        super(world);
    }

    // Shadow fields and methods from SlashBlade mod
    @Final
    @Shadow
    private static final DataParameter<ItemStack> WatchIndexBlade
            = EntityDataManager.createKey(MixinEntityBladeStand.class, DataSerializers.ITEM_STACK);
    @Final
    @Shadow
    private static final DataParameter<Integer> WatchIndexFlipState
            = EntityDataManager.createKey(MixinEntityBladeStand.class, DataSerializers.VARINT);
    @Final
    @Shadow
    private static final DataParameter<Integer> WatchIndexStandType
            = EntityDataManager.createKey(MixinEntityBladeStand.class, DataSerializers.VARINT);
    @Shadow
    public boolean setStandBlade(Entity e) {return false;}
    @Shadow
    public boolean hasBlade() {return false;}

    @Unique
    private static final DataParameter<ItemStack> sjap$originalFenceItem =
        EntityDataManager.createKey(MixinEntityBladeStand.class, DataSerializers.ITEM_STACK);
    public ItemStack sjap$getOriginalFenceItem(){
        return this.getDataManager().get(sjap$originalFenceItem);
    }
    public void sjap$setOriginalFenceItem(ItemStack stack) { this.getDataManager().set(sjap$originalFenceItem, stack); }

    /**
     * @author RisingInIris2017
     * @reason Add a new register entry for original fence item
     */
    @Overwrite
    protected void entityInit() {
        this.getDataManager().register(WatchIndexBlade, ItemStack.EMPTY);
        this.getDataManager().register(WatchIndexFlipState, 0);
        this.getDataManager().register(WatchIndexStandType, 0);
        this.getDataManager().register(sjap$originalFenceItem, ItemStack.EMPTY);
    }
    /**
     * @author RisingInIris2017
     * @reason Previous @Inject method does not work properly.
     */
    @Overwrite
    public boolean hitByEntity(Entity entity) {
        if (this.setStandBlade(entity)) {
            return true;
        } else if (!this.hasBlade() && entity.isSneaking()) {
            if (!entity.world.isRemote) {
                EntityItem droppedFenceItem = new EntityItem(entity.world, this.posX, this.posY, this.posZ, this.sjap$getOriginalFenceItem());
                world.spawnEntity(droppedFenceItem);
                this.setDead();
            }
            return true;
        } else {
            return super.hitByEntity(entity);
        }
    }
}
