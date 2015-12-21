package be.mdeswert.forge.mods.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static be.mdeswert.forge.mods.trampolines.Main.MODID;

public class BlockUtils {
    public static Block getBlockUnderneathEntity(Entity entity) {
        BlockPos blockPos = new BlockPos(entity.posX,
                entity.posY - 1,
                entity.posZ);
        return entity.worldObj.getBlockState(blockPos).getBlock();
    }

    public static void registerBlock(Block block, String name) {
        GameRegistry.registerBlock(block, name);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(getItem(name), 0, getModelResourceLocation(name));
    }

    private static Item getItem(String name) {
        return GameRegistry.findItem(MODID, name);
    }

    private static ModelResourceLocation getModelResourceLocation(String name) {
        return new ModelResourceLocation(MODID + ":" + name, "inventory");
    }
}
