package net.pl3x.map.fabric.mixin;

import net.minecraft.client.render.MapRenderer;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.item.map.MapState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MapRenderer.MapTexture.class)
public class MapTextureMixin {
    @Final
    @Shadow
    private NativeImageBackedTexture texture;
    @Shadow
    private MapState state;
    @Shadow
    private boolean needsUpdate;

    private int id;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injected(MapRenderer outer, int id, MapState state, CallbackInfo ci) {
        this.id = id;
    }

    @Inject(method = "updateTexture()V", at = @At("HEAD"), cancellable = true)
    private void injected(CallbackInfo ci) {
        // todo
    }
}
