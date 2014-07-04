package io.github.andrepl.chatlib;


import net.minecraft.server.v1_7_R3.ChatClickable;
import net.minecraft.server.v1_7_R3.ChatComponentText;
import net.minecraft.server.v1_7_R3.ChatHoverable;
import net.minecraft.server.v1_7_R3.EnumChatFormat;
import net.minecraft.server.v1_7_R3.IChatBaseComponent;
import net.minecraft.server.v1_7_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Text extends ChatComponentText {

	public Text append(String text) {
		return (Text) a(text);
	}

	public Text append(IChatBaseComponent node) {
		return (Text) a(node);
	}

	public Text append(IChatBaseComponent... nodes) {
		for (IChatBaseComponent node : nodes) {
			a(node);
		}
		return this;
	}

	public Trans fromItemStack(ItemStack stack) {
		return Util.fromItemStack(stack);
	}

	public Text appendItem(ItemStack stack) {
		return append(Util.fromItemStack(stack));
	}

	public Text setBold(boolean bold) {
		getChatModifier().setBold(bold);
		return this;
	}

	public Text setItalic(boolean italic) {
		getChatModifier().setItalic(italic);
		return this;
	}

	public Text setUnderline(boolean underline) {
		getChatModifier().setUnderline(underline);
		return this;
	}

	public Text setRandom(boolean random) {
		getChatModifier().setRandom(random);
		return this;
	}

	public Text setStrikethrough(boolean strikethrough) {
		getChatModifier().setStrikethrough(strikethrough);
		return this;
	}

	public Text setColor(ChatColor color) {
		getChatModifier().setColor(EnumChatFormat.valueOf(color.name()));
		return this;
	}

	public Text setClick(ClickAction action, String value) {
		this.getChatModifier().setChatClickable(new ChatClickable(action.getNMS(), value));
		return this;
	}

	public Text setHover(HoverAction action, IChatBaseComponent value) {
		getChatModifier().a(new ChatHoverable(action.getNMS(), value));
		return this;
	}

	public Text setHoverText(String text) {
		return setHover(HoverAction.SHOW_TEXT, new Text(text));
	}

	public Text(String s) {
		super(s);
	}

	@Override
	public IChatBaseComponent f() {
		return h();
	}

	public void send(Player player) {
		PacketPlayOutChat packet = new PacketPlayOutChat(this, true);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}