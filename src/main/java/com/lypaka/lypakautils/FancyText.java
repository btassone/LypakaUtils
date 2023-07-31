package com.lypaka.lypakautils;

import net.minecraft.util.text.Color;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

import java.awt.*;

public class FancyText {

    /**
     * Takes a String with formatting codes (like &e for yellow) and replaces the & with the UTF thing to make the text yellow.
     * Use this for ItemStack display names.
     * @param unformattedString
     * @return String object with formatted text
     */
    public static String getFormattedString (String unformattedString) {

        return unformattedString.replace("&", "\u00a7");

    }

    /**
     * Takes a String with formatting codes, replaces those codes with the UTF shit, and returns a TextComponentString with formatting.
     * Use this for sending messages to players, console, ItemStack lore, stuff like that.
     * @param unformattedText
     * @return
     */
    public static StringTextComponent getFormattedText (String unformattedText) {

        return new StringTextComponent(getFormattedString(unformattedText));

    }

    public static StringTextComponent getFromHEX (String hexCode, String text) {

        StringTextComponent textComponent = new StringTextComponent(text);
        Style style = textComponent.getStyle();
        style.withColor(Color.fromRgb(java.awt.Color.decode(hexCode).getRGB()));
        textComponent.setStyle(style);
        return textComponent;

    }

}
