package net.kav.kav_soul_like.mixin;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.kav_soul_like.Kav_soul_like;

import net.kav.kav_soul_like.networking.packet.Packets;

import net.kav.kav_soul_like.util.IExampleAnimatedPlayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class PlayerAnimationExample {
    @Inject(method = "use", at = @At("HEAD"))
    private void playAnimation(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        //We must start the animation on client-side
        if (world.isClient) {
            var itemStack = user.getStackInHand(hand);
            //Do some filtering
            if (itemStack.getItem().equals(Items.PAPER)) {
                //If we want to play the animation, get the animation container
                var animationContainer = ((IExampleAnimatedPlayer) user).modid_getModAnimation();

                //Use setAnimation to set the current animation. It will be played automatically.
                KeyframeAnimation anim = PlayerAnimationRegistry.getAnimation(new Identifier(Kav_soul_like.MOD_ID, "waving"));




                //ClientPlayNetworking.send(ModMessages.WAVING,);


                //Use animationContainer.replaceAnimationWithFade(); to create fading effects instead of sudden changes.
            }
        }

        // For server-side animation playing, implement your own plugin channel or see Emotecraft API
        // https://github.com/KosmX/emotes/tree/dev/emotesAPI/src/main/java/io/github/kosmx/emotes/api/events
    }


}
