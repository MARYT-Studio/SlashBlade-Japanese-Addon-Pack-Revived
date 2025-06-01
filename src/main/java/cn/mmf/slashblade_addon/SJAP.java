package cn.mmf.slashblade_addon;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Tags.MOD_ID,
		name = SJAP.MOD_NAME,
		version = Tags.VERSION,
		acceptedMinecraftVersions = "1.12.2",
	 	dependencies = "required-after:flammpfeil.slashblade@[mc1.12-r32,);required-after:mm_lib;required-after:mixinbooter@[10.2,)"
		)
public class SJAP {
	public static final String MOD_ID = Tags.MOD_ID;
	public static final String MOD_NAME = Tags.MOD_NAME;

	@Instance(SJAP.MOD_ID)
	public static SJAP instance;

	@SidedProxy(clientSide = "cn.mmf.slashblade_addon.ClientProxy", serverSide = "cn.mmf.slashblade_addon.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
