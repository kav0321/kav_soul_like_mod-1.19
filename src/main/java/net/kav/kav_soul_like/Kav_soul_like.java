package net.kav.kav_soul_like;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.kav.kav_soul_like.Items.ModItems;
import net.kav.kav_soul_like.block.ModBlocks;
import net.kav.kav_soul_like.block.entity.ModBlockEntities;
import net.kav.kav_soul_like.command.Levelsetcommand;
import net.kav.kav_soul_like.config.ModConfigs;
import net.kav.kav_soul_like.event.Servertick;
import net.kav.kav_soul_like.event.entityattack;
import net.kav.kav_soul_like.event.playerdeath;
import net.kav.kav_soul_like.json_reader.JsonReader;
import net.kav.kav_soul_like.networking.ModMessages;
import net.kav.kav_soul_like.util.ModRegistries;
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
		ModRegistries.INIT();
		ModBlockEntities.registerBlockEntities();
		JsonReader.init();
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new entityattack());
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModMessages.registerC2SPackets();
		ServerPlayerEvents.AFTER_RESPAWN.register(new playerdeath());
		ServerTickEvents.END_WORLD_TICK.register(new Servertick());
		LOGGER.info("Hello Fabric world!");
	}
}
