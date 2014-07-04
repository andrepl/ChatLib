package io.github.andrepl.chatlib;

import net.minecraft.server.v1_7_R3.ChatSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChatComponentTest extends AbstractTestingBase {

	@Test
	public void appendItemEqualsFromItemThenAppend() {

		assertEquals(
			new Text("").append(Util.fromItemStack(new ItemStack(Material.LOG))),
			new Text("").appendItem(new ItemStack(Material.LOG))
		);

		assertEquals(
			new Trans("").append(Util.fromItemStack(new ItemStack(Material.LOG))),
			new Trans("").appendItem(new ItemStack(Material.LOG))
		);

	}

	@Test
	public void fromItemStackUsesTranslation() {
		assertTrue(ChatSerializer.a(Util.fromItemStack(new ItemStack(Material.LOG))).indexOf("\"translate\":\"tile.log.oak.name\"") != -1);
	}

	@Test
	public void fromItemStackUsesCustomDisplayName() {
		ItemStack stack = new ItemStack(Material.LOG);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("Oke Lawg");
		stack.setItemMeta(meta);
		assertTrue(ChatSerializer.a(Util.fromItemStack(stack)).indexOf("\"translate\":\"tile.log.oak.name\"") == -1);
		assertTrue(ChatSerializer.a(Util.fromItemStack(stack)).indexOf("\"translate\":\"Oke Lawg\"") != -1);
	}

	@Test
	public void appendMultiple() {
		assertEquals(
			new Text("1").append(new Text("2")).append(new Text("3")).append(new Text("4")),
			new Text("1").append(new Text("2"), new Text("3"), new Text("4"))
		);
	}

	@Test
	public void transWithVariables() {
		String asJson = ChatSerializer.a(new Trans("book.pageIndicator", 1, 2));
		assertTrue(asJson.indexOf("\"translate\":\"book.pageIndicator\"") != -1);
		assertTrue(asJson.indexOf("\"with\":[\"1\",\"2\"]") != -1);
	}

}
