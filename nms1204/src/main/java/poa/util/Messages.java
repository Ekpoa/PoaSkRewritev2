package poa.util;

public class Messages {
    public static String essentialsToMinimessage(String string) {

        String x = string.replaceAll("§", "&");

        x = x.replaceAll("&#([a-fA-F0-9]{6})", "<#$1><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("§", "&");
        x = x.replace("&l", "<bold>");
        x = x.replace("&n", "<underlined>");
        x = x.replace("&m", "<strikethrough>");
        x = x.replace("&o", "<italic>");
        x = x.replace("&k", "<obfuscated>");
        x = x.replace("&r", "<reset>");
        x = x.replace("&0", "<black><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&1", "<dark_blue><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&2", "<dark_green><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&3", "<dark_aqua><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&4", "<dark_red><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&5", "<dark_purple><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&6", "<gold><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&7", "<gray><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&8", "<dark_gray><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&9", "<blue><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&a", "<green><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&b", "<aqua><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&c", "<red><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&d", "<light_purple><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&e", "<yellow><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");
        x = x.replace("&f", "<white><bold:false><underlined:false><strikethrough:false><italic:false><obfuscated:false>");

        return x;
    }
}
