package de.nandi.nandicloud.core.util;

import java.awt.*;
import java.util.regex.Pattern;

public class ChatColorUtil {

	private static final char COLOR_CHAR = '§';
	private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + COLOR_CHAR + "[0-9A-FK-OR]");

	private static final String[] xtermHex = {"#000000", "#800000", "#008000", "#808000", "#000080", "#800080", "#008080", "#c0c0c0", "#808080", "#ff0000", "#00ff00", "#ffff00", "#0000ff", "#ff00ff", "#00ffff", "#ffffff", "#000000", "#00005f", "#000087", "#0000af", "#0000d7", "#0000ff", "#005f00", "#005f5f", "#005f87", "#005faf", "#005fd7", "#005fff", "#008700", "#00875f", "#008787", "#0087af", "#0087d7", "#0087ff", "#00af00", "#00af5f", "#00af87", "#00afaf", "#00afd7", "#00afff", "#00d700", "#00d75f", "#00d787", "#00d7af", "#00d7d7", "#00d7ff", "#00ff00", "#00ff5f", "#00ff87", "#00ffaf", "#00ffd7", "#00ffff", "#5f0000", "#5f005f", "#5f0087", "#5f00af", "#5f00d7", "#5f00ff", "#5f5f00", "#5f5f5f", "#5f5f87", "#5f5faf", "#5f5fd7", "#5f5fff", "#5f8700", "#5f875f", "#5f8787", "#5f87af", "#5f87d7", "#5f87ff", "#5faf00", "#5faf5f", "#5faf87", "#5fafaf", "#5fafd7", "#5fafff", "#5fd700", "#5fd75f", "#5fd787", "#5fd7af", "#5fd7d7", "#5fd7ff", "#5fff00", "#5fff5f", "#5fff87", "#5fffaf", "#5fffd7", "#5fffff", "#870000", "#87005f", "#870087", "#8700af", "#8700d7", "#8700ff", "#875f00", "#875f5f", "#875f87", "#875faf", "#875fd7", "#875fff", "#878700", "#87875f", "#878787", "#8787af", "#8787d7", "#8787ff", "#87af00", "#87af5f", "#87af87", "#87afaf", "#87afd7", "#87afff", "#87d700", "#87d75f", "#87d787", "#87d7af", "#87d7d7", "#87d7ff", "#87ff00", "#87ff5f", "#87ff87", "#87ffaf", "#87ffd7", "#87ffff", "#af0000", "#af005f", "#af0087", "#af00af", "#af00d7", "#af00ff", "#af5f00", "#af5f5f", "#af5f87", "#af5faf", "#af5fd7", "#af5fff", "#af8700", "#af875f", "#af8787", "#af87af", "#af87d7", "#af87ff", "#afaf00", "#afaf5f", "#afaf87", "#afafaf", "#afafd7", "#afafff", "#afd700", "#afd75f", "#afd787", "#afd7af", "#afd7d7", "#afd7ff", "#afff00", "#afff5f", "#afff87", "#afffaf", "#afffd7", "#afffff", "#d70000", "#d7005f", "#d70087", "#d700af", "#d700d7", "#d700ff", "#d75f00", "#d75f5f", "#d75f87", "#d75faf", "#d75fd7", "#d75fff", "#d78700", "#d7875f", "#d78787", "#d787af", "#d787d7", "#d787ff", "#d7af00", "#d7af5f", "#d7af87", "#d7afaf", "#d7afd7", "#d7afff", "#d7d700", "#d7d75f", "#d7d787", "#d7d7af", "#d7d7d7", "#d7d7ff", "#d7ff00", "#d7ff5f", "#d7ff87", "#d7ffaf", "#d7ffd7", "#d7ffff", "#ff0000", "#ff005f", "#ff0087", "#ff00af", "#ff00d7", "#ff00ff", "#ff5f00", "#ff5f5f", "#ff5f87", "#ff5faf", "#ff5fd7", "#ff5fff", "#ff8700", "#ff875f", "#ff8787", "#ff87af", "#ff87d7", "#ff87ff", "#ffaf00", "#ffaf5f", "#ffaf87", "#ffafaf", "#ffafd7", "#ffafff", "#ffd700", "#ffd75f", "#ffd787", "#ffd7af", "#ffd7d7", "#ffd7ff", "#ffff00", "#ffff5f", "#ffff87", "#ffffaf", "#ffffd7", "#ffffff", "#080808", "#121212", "#1c1c1c", "#262626", "#303030", "#3a3a3a", "#444444", "#4e4e4e", "#585858", "#606060", "#666666", "#767676", "#808080", "#8a8a8a", "#949494", "#9e9e9e", "#a8a8a8", "#b2b2b2", "#bcbcbc", "#c6c6c6", "#d0d0d0", "#dadada", "#e4e4e4", "#eeeeee"};

	private static final String ANSI_RESET = "\u001B[0m";

	public static String stripColors(String input) {
		if (input == null) return null;
		return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
	}

	public static String toLegacyText(String input) {
		return stripColors(input
				.replace("§0", ANSI_RESET) // Standard color
				.replace("§1", rgbToAnsi(0, 0, 170))
				.replace("§2", rgbToAnsi(0, 170, 0))
				.replace("§3", rgbToAnsi(0, 170, 170))
				.replace("§4", rgbToAnsi(170, 0, 0))
				.replace("§5", rgbToAnsi(170, 0, 170))
				.replace("§6", rgbToAnsi(255, 170, 0))
				.replace("§7", rgbToAnsi(170, 170, 170))
				.replace("§8", rgbToAnsi(85, 85, 85))
				.replace("§9", rgbToAnsi(85, 85, 255))
				.replace("§a", rgbToAnsi(85, 255, 85))
				.replace("§b", rgbToAnsi(4, 249, 249))
				.replace("§c", rgbToAnsi(255, 85, 85))
				.replace("§d", rgbToAnsi(255, 85, 255))
				.replace("§e", rgbToAnsi(255, 255, 0))
				.replace("§f", ANSI_RESET)
				.replace("§r", ANSI_RESET)// Mostly, white isn't readable, so we want the standard color
		);
	}

	private static String rgbToAnsi(int r, int g, int b) {
		return xtermToAnsi(rgbToXterm(r, g, b));
	}

	private static int rgbToXterm(int r, int g, int b) {
		double best = 99999999.9;
		int bestId = 0;
		for (int i = 0; i < xtermHex.length; i++) {
			Color c = hex2Rgb(xtermHex[i]);
			double dif = rgbDifference(r, g, b, c.getRed(), c.getGreen(), c.getBlue());
			if (dif < best) {
				best = dif;
				bestId = i;
			}
		}
		return bestId;
	}

	private static String xtermToAnsi(int id) {
		return "\033[38;5;" + id + "m";
	}

	private static double rgbDifference(int ar, int ag, int ab, int br, int bg, int bb) {
		return Math.sqrt(Math.pow(ar - br, 2) + Math.pow(ag - bg, 2) + Math.pow(ab - bb, 2));
	}

	private static Color hex2Rgb(String hex) {
		return new Color(
				Integer.valueOf(hex.substring(1, 3), 16),
				Integer.valueOf(hex.substring(3, 5), 16),
				Integer.valueOf(hex.substring(5, 7), 16));
	}

	public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
		char[] b = textToTranslate.toCharArray();

		for (int i = 0; i < b.length - 1; ++i) {
			if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
				b[i] = COLOR_CHAR;
				b[i + 1] = Character.toLowerCase(b[i + 1]);
			}
		}

		return new String(b);
	}

}
