package io.github.andrepl.chatlib;

import net.minecraft.server.v1_7_R3.Item;
import org.bukkit.craftbukkit.v1_7_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lang {

	/**
	 * This class provides methods to translate enchantments, items and other
	 * translatable strings into the language of the minecraft/craftbukkit server,
	 * as well as retrieving the translatable representations of Items and Enchantments
	 * to be sent to the client using raw chat message.
	 *
	 * The complete listing of translations can be found in your craftbukkit,
	 * minecraft, or minecraft_server jar at /assets/minecraft/lang/
	 */
	private static Pattern pat = Pattern.compile("^\\s*([\\w\\d\\.]+)\\s*=\\s*(.*)\\s*$");
	private static Map<String, String> translations;
	private static String language = null;

	/**
	 * This must be called after Craftbukkit loads, before you attempt to
	 * access any other methods of this class.
	 *
	 * @throws IOException
	 */
	public static void initialize(String lang) throws IOException {
		translations = new HashMap<String, String>();
		if (lang == null) {
			lang = "en_US";
		}
		if (!lang.equals(language)) {
			language = lang;
			String resourcePath = "/assets/minecraft/lang/" + language + ".lang";
			InputStream fis = Item.class.getResourceAsStream(resourcePath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String line;
			Matcher matcher;
			while((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.contains("=")) {
					matcher = pat.matcher(line);
					if (matcher.matches()) {
						translations.put(matcher.group(1), matcher.group(2));
					}
				}
			}
		}
	}

	/**
	 * get the currently loaded language code. (eg. 'en_US')
	 * @return the language code
	 */
	public static String getLanguage() {
		return language;
	}

	/**
	 * get the translatable string representing the name of the provided item stack.
	 *   eg.
	 *     Lang.translatableFromStack(new ItemStack(Material.INK_SACK, 1, 4))
	 *     -> "item.dye.blue"
	 *
	 * @param stack - a Bukkit ItemStack
	 * @return the translatable string representing the item name.
	 */
	public static String translatableFromStack(ItemStack stack) {
		net.minecraft.server.v1_7_R3.ItemStack nms = CraftItemStack.asNMSCopy(stack);
		Item item = nms.getItem();
		return item.a(nms);
	}
	/**
	 * get the name of the provided item translated to the server's language
	 *   eg.
	 *     Lang.fromStack(new ItemStack(Material.INK_SACK, 1, 4))
	 *     -> "Lapis Lazuli"
	 *
	 * @param stack - a Bukkit ItemStack
	 * @return translated name of the item.
	 */
	public static String fromStack(ItemStack stack) {
		String node = translatableFromStack(stack);
		String val = translations.get(node);
		if (val == null) {
			return node;
		}
		return val;
	}

	/**
	 * get the translatable string representing the name of the provided enchantment
	 *   eg.
	 *     Lang.translatableFromEnchantment(Enchantment.DIG_SPEED)
	 *     -> "enchantment.digging"
	 *
	 * @param ench - a Bukkit Enchantment
	 * @return the translatable string representing the name of the enchantment.
	 */
	public static String translatableFromEnchantment(Enchantment ench) {

		net.minecraft.server.v1_7_R3.Enchantment nms = net.minecraft.server.v1_7_R3.Enchantment.byId[ench.getId()];
		if (nms == null) {
			return ench.getName();
		} else {
			return nms.a();
		}
	}

	/**
	 * get the name of the provided enchantment translated to the server's language
	 *   eg.
	 *     Lang.fromEnchantment(Enchantment.DIG_SPEED)
	 *     -> "enchantment.digging"
	 * @param ench - a Bukkit Enchantment
	 * @return - translated name of the enchantment.
	 */
	public static String fromEnchantment(Enchantment ench) {
		String node = translatableFromEnchantment(ench);
		String val = translations.get(node);
		if (val == null) {
			return node;
		}
		return val;
	}

	/**
	 * translate the provided translatable string into the server's language,
	 *
	 * if args are provided, the translated string must contain the same number of
	 * placeholders, the args will be substituted using String.format()
	 *   eg.
	 *     Lang.translate("record.nowPlaying", "My favourite Song");
	 *     -> "Now playing: My Favourite Song"
	 *
	 * @param key - the translatable string. eg.
	 * @param args - objects to be formatted into the translated string.
	 * @return the translated string.
	 */
	public static String translate(String key, Object... args) {
		return String.format(translations.get(key), args);
	}

}