package com.donutstunnelbasefinder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = TunnelBaseFinder.MODID, name = TunnelBaseFinder.NAME, version = TunnelBaseFinder.VERSION)
public class TunnelBaseFinder {
    public static final String MODID = "tunnelbasefinder";
    public static final String NAME = "Tunnel Base Finder";
    public static final String VERSION = "1.0";

    private static final List<BlockPos> storageBlocks = new ArrayList<>();
    private static final int REQUIRED_STORAGE_BLOCKS = 50; // Adjust this value as needed
    private static boolean baseFound = false;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (event.getWorld().isRemote) {
            storageBlocks.clear();
            baseFound = false;
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;

        if (!baseFound) {
            findBase(world);
        }
    }

    private void findBase(World world) {
        BlockPos playerPos = Minecraft.getMinecraft
