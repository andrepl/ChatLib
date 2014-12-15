package org.bukkit.support;

import net.minecraft.server.v1_8_R1.Enchantment;

public class DummyEnchantments {

    static {
        Enchantment.b.getClass();
        org.bukkit.enchantments.Enchantment.stopAcceptingRegistrations();
    }

    public static void setup() {}
}
