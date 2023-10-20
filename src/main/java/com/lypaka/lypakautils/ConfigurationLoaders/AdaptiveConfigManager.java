package com.lypaka.lypakautils.ConfigurationLoaders;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

/**
 *
 * Used to create essentially an "in-between" configuration loader between the BasicConfigManager and ComplexConfigManager
 * Creates configuration files from a template file like the ComplexConfigManager but is not stored as a list like the ComplexConfigManager
 * Able to be gotten individually like the BasicConfigManager
 * Based on original design by landonjw of Cobblemon.
 * @author landonjw, Lypaka
 *
 */
public class AdaptiveConfigManager {

    private final String[] fileNames;
    private final String templateFile;
    private final Path configPath;
    private final Class mainClass;
    private final String modName;
    private final String modID;
    private final Logger logger;
    private final Path[] config;
    private final ArrayList<ConfigurationLoader<CommentedConfigurationNode>> configLoad;
    private final CommentedConfigurationNode[] configNode;

    public AdaptiveConfigManager (String[] fileNames, String templateFile, Path configPath, Class mainClass, String modName, String modID, Logger logger) {

        this.fileNames = fileNames;
        this.templateFile = templateFile;
        this.configPath = configPath;
        this.mainClass = mainClass;
        this.modName = modName;
        this.modID = modID;
        this.logger = logger;

        this.config = new Path[this.fileNames.length];
        this.configLoad = new ArrayList<ConfigurationLoader<CommentedConfigurationNode>>(this.fileNames.length);
        this.configNode = new CommentedConfigurationNode[this.fileNames.length];

    }

    public void init() throws IOException {

        for (int i = 0; i < this.fileNames.length; i++) {

            this.config[i] = this.configPath.resolve(this.fileNames[i]);
            Path filePathString = Paths.get(this.configPath + "//" + this.fileNames[i]);
            if (!this.config[i].toFile().exists()) {

                try {

                    Files.copy(this.mainClass.getClassLoader().getResourceAsStream("assets/" + this.modID + "/" + this.templateFile), filePathString, StandardCopyOption.REPLACE_EXISTING);

                } catch (DirectoryNotEmptyException er) {


                }

            }

            HoconConfigurationLoader.builder().setPath(this.configPath).setFile(new File(this.fileNames[i])).build();
            config[i] = this.configPath.resolve(this.fileNames[i]);

        }

        load();

    }



    public void load() {

        try {

            for (int i = 0; i < this.fileNames.length; i++) {

                configLoad.add(i, HoconConfigurationLoader.builder().setPath(config[i]).build());
                configNode[i] = HoconConfigurationLoader.builder().setPath(config[i]).build().load();

            }

        } catch (IOException e) {

            this.logger.error(this.modName + " configuration could not load.");
            e.printStackTrace();

        }

    }

    public void save() {

        for (int i = 0; i < this.fileNames.length; i++) {

            try {

                configLoad.get(i).save(configNode[i]);

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    public CommentedConfigurationNode getConfigNode (int index, Object... node) {

        return configNode[index].getNode(node);

    }

}
