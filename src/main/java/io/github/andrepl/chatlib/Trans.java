package io.github.andrepl.chatlib;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class Trans extends ChatMessage {

	public Trans(String s, Object... objects) {
		super(s, objects);
	}

	@Override
	public IChatBaseComponent f() {
		return h();
	}

	public Trans append(String text) {
		return (Trans) a(text);
	}

	public Trans append(IChatBaseComponent node) {
		return (Trans) addSibling(node);
	}

	public Trans append(IChatBaseComponent... nodes) {
		for (IChatBaseComponent node : nodes) {
			addSibling(node);
		}
		return this;
	}

	public Trans appendItem(ItemStack stack) {
		return append(Util.fromItemStack(stack));
	}

	public static Trans fromItemStack(ItemStack stack) {
		return Util.fromItemStack(stack);
	}

	public Trans setBold(boolean bold) {
		getChatModifier().setBold(bold);
		return this;
	}

	public Trans setItalic(boolean italic) {
		getChatModifier().setItalic(italic);
		return this;
	}

	public Trans setUnderline(boolean underline) {
		getChatModifier().setUnderline(underline);
		return this;
	}

	public Trans setRandom(boolean random) {
		getChatModifier().setRandom(random);
		return this;
	}

	public Trans setStrikethrough(boolean strikethrough) {
		getChatModifier().setStrikethrough(strikethrough);
		return this;
	}

	public Trans setColor(ChatColor color) {
		getChatModifier().setColor(EnumChatFormat.valueOf(color.name()));
		return this;
	}

	public Trans setClick(ClickAction action, String value) {
		getChatModifier().setChatClickable(new ChatClickable(action.getNMS(), value));
		return this;
	}

	public Trans setHover(HoverAction action, IChatBaseComponent value) {
        getChatModifier().setChatHoverable(new ChatHoverable(action.getNMS(), value));
        return this;
	}

	public Trans setHoverText(String text) {
		return setHover(HoverAction.SHOW_TEXT, new Text(text));
	}

	public void send(CommandSender sender) {
		Util.send(sender, this);
	}
}
