package org.bukkit.support;

import net.minecraft.server.v1_7_R3.MobEffectList;

import org.bukkit.craftbukkit.v1_7_R3.potion.CraftPotionBrewer;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;

public class DummyPotions {
    static {
        Potion.setPotionBrewer(new CraftPotionBrewer());
        MobEffectList.BLINDNESS.getClass();
        PotionEffectType.stopAcceptingRegistrations();
    }

    public static void setup() {}
}
