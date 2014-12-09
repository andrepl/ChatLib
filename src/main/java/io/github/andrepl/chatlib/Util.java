package io.github.andrepl.chatlib;

import net.minecraft.server.v1_8_R1.ChatComponentText;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util {

    public static String getName(net.minecraft.server.v1_8_R1.ItemStack stack) {
        NBTTagCompound tag = stack.getTag();

        if ((tag != null) && (tag.hasKeyOfType("display", 10))) {
            NBTTagCompound nbttagcompound = tag.getCompound("display");

            if (nbttagcompound.hasKeyOfType("Name", 8)) {
                return nbttagcompound.getString("Name");
            }
        }

        return stack.getItem().a(stack) + ".name";
    }

    public static Trans fromItemStack(ItemStack stack) {
        net.minecraft.server.v1_8_R1.ItemStack nms = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound tag = new NBTTagCompound();
        nms.save(tag);
        return new Trans(getName(nms)).
                setColor(ChatColor.getByChar(nms.u().e.name())).
                setHover(HoverAction.SHOW_ITEM, new ChatComponentText(tag.toString()));
    }

    public static void send(CommandSender sender, IChatBaseComponent text, ChatPosition position) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PacketPlayOutChat packet = new PacketPlayOutChat(text, position.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        } else {
            sender.sendMessage(text.c());
        }
    }
}
