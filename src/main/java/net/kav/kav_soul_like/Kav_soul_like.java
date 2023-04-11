package net.kav.kav_soul_like;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.kav.kav_soul_like.Items.ModItems;
import net.kav.kav_soul_like.block.ModBlocks;
import net.kav.kav_soul_like.config.ModConfigs;
import net.kav.kav_soul_like.event.playerdeath;
import net.kav.kav_soul_like.networking.ModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kav_soul_like implements ModInitializer {
	public static final String MOD_ID="kav_soul_like";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModMessages.registerC2SPackets();
		ServerPlayerEvents.AFTER_RESPAWN.register(new playerdeath());
		//ServerTickEvents.END_SERVER_TICK.register(new serverevent());
		LOGGER.info("Hello Fabric world!");
	}
}
