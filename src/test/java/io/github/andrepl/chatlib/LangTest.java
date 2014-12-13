package io.github.andrepl.chatlib;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.support.AbstractTestingBase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LangTest extends AbstractTestingBase {

	@BeforeClass
	public static void setupLang() throws IOException {
		Lang.initialize(null);
	}

	@Test
	public void translatableFromStack() {
		assertEquals("item.dyePowder.lime.name", Lang.translatableFromStack(new ItemStack(Material.INK_SACK, 0, (byte) 10)));
	}

	@Test
	public void fromStack() {
		assertEquals("Lime Dye", Lang.fromStack(new ItemStack(Material.INK_SACK, 0, (byte) 10)));
	}

	@Test
	public void translatableFromEnchantment() {
		assertEquals("enchantment.lootBonus", Lang.translatableFromEnchantment(Enchantment.LOOT_BONUS_MOBS));
	}

	@Test
	public void fromEnchantment() {
		assertEquals("Looting", Lang.fromEnchantment(Enchantment.LOOT_BONUS_MOBS));
	}

	@Test
	public void translatableFromPotionEffectType() {
		assertEquals("potion.digSpeed", Lang.translatableFromPotionEffectType(PotionEffectType.FAST_DIGGING));
	}

	@Test
	public void FromPotionEffectType() {
		assertEquals("Haste", Lang.fromPotionEffectType(PotionEffectType.FAST_DIGGING));
	}

	@Test
	public void translateWithVars() {
		assertEquals("Page 1 of 10", Lang.translate("book.pageIndicator", 1, 10));
	}
}
