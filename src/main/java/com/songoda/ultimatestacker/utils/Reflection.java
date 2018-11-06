package com.songoda.ultimatestacker.utils;

import org.bukkit.Bukkit;
import org.bukkit.block.CreatureSpawner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {
    private static Class<?> clazzCraftCreatureSpawner, clazzTileEntityMobSpawner = null;
    private static Method methodGetTileEntity, methodGetSpawner;
    private static Field fieldSpawnRange;

    public static CreatureSpawner setRange(CreatureSpawner creatureSpawner, int amount) {
        try {
            if (clazzCraftCreatureSpawner == null) {
                String ver = Bukkit.getServer().getClass().getPackage().getName().substring(23);
                clazzCraftCreatureSpawner = Class.forName("org.bukkit.craftbukkit." + ver + ".block.CraftCreatureSpawner");
                clazzTileEntityMobSpawner = Class.forName("net.minecraft.server." + ver + ".TileEntityMobSpawner");
                Class<?> clazzMobSpawnerAbstract = Class.forName("net.minecraft.server." + ver + ".MobSpawnerAbstract");
                methodGetTileEntity = clazzCraftCreatureSpawner.getDeclaredMethod("getTileEntity");
                methodGetSpawner = clazzTileEntityMobSpawner.getDeclaredMethod("getSpawner");
                fieldSpawnRange = clazzMobSpawnerAbstract.getDeclaredField("spawnRange");
                fieldSpawnRange.setAccessible(true);
            }

            Object objCraftCreatureSpawner = clazzCraftCreatureSpawner.cast(creatureSpawner);
            Object objTileEntityMobSpawner = clazzTileEntityMobSpawner.cast(methodGetTileEntity.invoke(objCraftCreatureSpawner));
            Object objMobSpawnerAbstract = methodGetSpawner.invoke(objTileEntityMobSpawner);
            fieldSpawnRange.set(objMobSpawnerAbstract, amount);

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return creatureSpawner;
    }


}
