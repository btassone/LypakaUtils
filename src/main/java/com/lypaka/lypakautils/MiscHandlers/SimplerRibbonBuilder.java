package com.lypaka.lypakautils.MiscHandlers;

import com.lypaka.lypakautils.FancyText;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.MutableRibbonData;
import com.pixelmonmod.pixelmon.api.util.ResourceWithFallback;
import com.pixelmonmod.pixelmon.api.util.helpers.ResourceLocationHelper;
import net.minecraft.util.text.ITextComponent;

public class SimplerRibbonBuilder {

    private final ITextComponent title; // REQUIRED OR CLIENT GO CRASHY CRASH WHEN OPENING RIBBON MENU IN POKEMON SUMMARY GUI
    private final ResourceWithFallback icon; // REQUIRED OR CLIENT GO CRASHY CRASH WHEN OPENING RIBBON MENU IN POKEMON SUMMARY GUI
    private final ITextComponent description; // REQUIRED OR CLIENT GO CRASHY CRASH WHEN OPENING RIBBON MENU IN POKEMON SUMMARY GUI
    private ITextComponent prefix = null;
    private ITextComponent suffix = null;
    private MutableRibbonData data = null;

    public SimplerRibbonBuilder (String title, String icon, String description) {

        this.title = FancyText.getFormattedText(title);
        this.icon = ResourceWithFallback.from(ResourceLocationHelper.of(Pixelmon.MODID, icon));
        this.description = FancyText.getFormattedText(description);

    }

    public SimplerRibbonBuilder (String title, String icon, String description, String prefix, String suffix) {

        this.title = FancyText.getFormattedText(title);
        this.icon = ResourceWithFallback.from(ResourceLocationHelper.of(Pixelmon.MODID, icon));
        this.description = FancyText.getFormattedText(description);
        this.prefix = FancyText.getFormattedText(prefix);
        this.suffix = FancyText.getFormattedText(suffix);

    }

    public MutableRibbonData create() {

        this.data = new MutableRibbonData();
        this.data.setIcon(this.icon);
        this.data.setTitle(this.title);
        this.data.setDescription(this.description);
        if (hasPrefix()) this.data.setPrefix(this.prefix);
        if (hasSuffix()) this.data.setSuffix(this.suffix);

        return this.data;

    }

    public void updateData() {

        if (hasPrefix()) this.data.setPrefix(this.prefix);
        if (hasSuffix()) this.data.setSuffix(this.suffix);

    }

    public ITextComponent getTitle() {

        return this.title;

    }

    public ResourceWithFallback getIcon() {

        return this.icon;

    }

    public ITextComponent getDescription() {

        return this.description;

    }

    public ITextComponent getPrefix() {

        return this.prefix;

    }

    public void setPrefix (String prefix) {

        this.prefix = FancyText.getFormattedText(prefix);

    }

    public ITextComponent getSuffix() {

        return this.suffix;

    }

    public void setSuffix (String suffix) {

        this.suffix = FancyText.getFormattedText(suffix);

    }

    public boolean hasPrefix() {

        return this.prefix != null;

    }

    public boolean hasSuffix() {

        return this.suffix != null;

    }

}
