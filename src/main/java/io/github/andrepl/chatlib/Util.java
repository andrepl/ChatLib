package io.github.andrepl.chatlib;

import net.minecraft.server.v1_7_R3.ChatComponentText;
import net.minecraft.server.v1_7_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Util {
	public static String getName(net.minecraft.server.v1_7_R3.ItemStack stack) {
		if ((stack.tag != null) && (stack.tag.hasKeyOfType("display", 10))) {
			NBTTagCompound nbttagcompound = stack.tag.getCompound("display");

			if (nbttagcompound.hasKeyOfType("Name", 8)) {
				return nbttagcompound.getString("Name");
			}
		}
		return stack.getItem().a(stack) + ".name";
	}

	public static Trans fromItemStack (ItemStack stack) {
		net.minecraft.server.v1_7_R3.ItemStack nms = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = new NBTTagCompound();
		nms.save(tag);
		return new Trans(getName(nms)).setColor(ChatColor.getByChar(nms.w().e.getChar())).setHover(HoverAction.SHOW_ITEM, new ChatComponentText(tag.toString()));
	}
}
