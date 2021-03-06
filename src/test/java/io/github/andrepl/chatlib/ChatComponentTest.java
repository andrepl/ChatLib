package io.github.andrepl.chatlib;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChatComponentTest extends AbstractTestingBase {

	@Test
	public void convenienceFromItemStack() {
		ItemStack stack = new ItemStack(Material.LOG);
		assertEquals(Util.fromItemStack(stack), Text.fromItemStack(stack));
		assertEquals(Util.fromItemStack(stack), Trans.fromItemStack(stack));
	}

	@Test
	public void appendItemEqualsFromItemThenAppend() {
		ItemStack stack = new ItemStack(Material.LOG);
		assertEquals(
			new Text("").append(Util.fromItemStack(stack)),
			new Text("").appendItem(stack)
		);

		assertEquals(
			new Trans("").append(Util.fromItemStack(stack)),
			new Trans("").appendItem(stack)
		);

	}

	@Test
	public void fromItemStackUsesTranslation() {
		ItemStack stack = new ItemStack(Material.LOG);
		assertTrue(ChatSerializer.a(Util.fromItemStack(stack)).contains("\"translate\":\"tile.log.oak.name\""));
	}

	@Test
	public void fromItemStackUsesCustomDisplayName() {
		ItemStack stack = new ItemStack(Material.LOG);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("Oke Lawg");
		stack.setItemMeta(meta);
		assertTrue(!ChatSerializer.a(Util.fromItemStack(stack)).contains("\"translate\":\"tile.log.oak.name\""));
		assertTrue(ChatSerializer.a(Util.fromItemStack(stack)).contains("\"translate\":\"Oke Lawg\""));
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
		assertTrue(asJson.contains("\"translate\":\"book.pageIndicator\""));
		assertTrue(asJson.contains("\"with\":[\"1\",\"2\"]"));
	}

}
