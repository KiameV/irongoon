package lod.irongoon.config;

import lod.irongoon.data.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class IrongoonConfig {
    private static final IrongoonConfig INSTANCE = new IrongoonConfig();
    public static IrongoonConfig getInstance() {
        return INSTANCE;
    }

    public final String externalDataLoadPath = "./mods/irongoon/US/";
    public final String externalDataLoadExtension = ".csv";
    public final String externalConfigLoadPath = "./mods/irongoon/config.yaml";
    public final String publicSeed;
    public final long seed;
    public final int bodyNumberOfStatsAmount = 4;
    public final int dragoonNumberOfStatsAmount = 4;
    public final TotalStatsPerLevel bodyTotalStatsPerLevel;
    public final TotalStatsPerLevel dragoonTotalStatsPerLevel;
    public final TotalStatsMonsters monsterTotalStatsPerLevel;
    public final int NonBaselineStatsUpperPercentBound;
    public final int NonBaselineStatsLowerPercentBound;
    public final int TotalStatsMonstersUpperPercentBound;
    public final int TotalStatsMonstersLowerPercentBound;
    public final TotalStatsBounds bodyTotalStatsBounds;
    public final TotalStatsBounds dragoonStatsBounds;
    public final TotalStatsDistributionPerLevel bodyTotalStatsDistributionPerLevel;
    public final TotalStatsDistributionPerLevel dragoonTotalStatsDistributionPerLevel;
    public final HPStatPerLevel hpStatPerLevel;
    public final SpeedStatPerLevel speedStatPerLevel;
    public final HPStatMonsters hpStatMonsters;
    public final SpeedStatMonsters speedStatMonsters;

    private IrongoonConfig() {
        File configFile = new File(externalConfigLoadPath);

        Map<String, Object> yamlConfig = null;
        try (InputStream inputStream = new FileInputStream(configFile)) {
            Yaml yaml = new Yaml();
            yamlConfig = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            yamlConfig = Map.of();
        }

        this.publicSeed = (String) yamlConfig.getOrDefault("publicSeed", "7963AC95E13873B6");
        this.seed = Long.parseUnsignedLong(publicSeed, 16);
        this.bodyTotalStatsPerLevel = TotalStatsPerLevel.valueOf((String) yamlConfig.getOrDefault("bodyTotalStatsPerLevel", "RANDOMIZE_BOUNDS_PER_LEVEL"));
        this.dragoonTotalStatsPerLevel = TotalStatsPerLevel.valueOf((String) yamlConfig.getOrDefault("dragoonTotalStatsPerLevel", "RANDOMIZE_BOUNDS_PER_LEVEL"));
        this.monsterTotalStatsPerLevel = TotalStatsMonsters.valueOf((String) yamlConfig.getOrDefault("monsterTotalStatsPerLevel", "RANDOMIZE_BOUNDS"));
        this.NonBaselineStatsUpperPercentBound = (int) yamlConfig.getOrDefault("nonBaselineStatsUpperPercentBound", 150);
        this.NonBaselineStatsLowerPercentBound = (int) yamlConfig.getOrDefault("nonBaselineStatsLowerPercentBound", 50);
        this.TotalStatsMonstersUpperPercentBound = (int) yamlConfig.getOrDefault("TotalStatsMonstersUpperPercentBound", 150);
        this.TotalStatsMonstersLowerPercentBound = (int) yamlConfig.getOrDefault("TotalStatsMonstersLowerPercentBound", 50);
        this.bodyTotalStatsBounds = TotalStatsBounds.valueOf((String) yamlConfig.getOrDefault("bodyTotalStatsBounds", "STOCK"));
        this.dragoonStatsBounds = TotalStatsBounds.valueOf((String) yamlConfig.getOrDefault("dragoonStatsBounds", "STOCK"));
        this.bodyTotalStatsDistributionPerLevel = TotalStatsDistributionPerLevel.valueOf((String) yamlConfig.getOrDefault("bodyTotalStatsDistributionPerLevel", "RANDOM"));
        this.dragoonTotalStatsDistributionPerLevel = TotalStatsDistributionPerLevel.valueOf((String) yamlConfig.getOrDefault("dragoonTotalStatsDistributionPerLevel", "RANDOM"));
        this.hpStatPerLevel = HPStatPerLevel.valueOf((String) yamlConfig.getOrDefault("hpStatPerLevel", "RANDOM"));
        this.speedStatPerLevel = SpeedStatPerLevel.valueOf((String) yamlConfig.getOrDefault("speedStatPerLevel", "RANDOM"));
        this.hpStatMonsters = HPStatMonsters.valueOf((String) yamlConfig.getOrDefault("hpStatMonster", "RANDOMIZE_BOUNDS"));
        this.speedStatMonsters = SpeedStatMonsters.valueOf((String) yamlConfig.getOrDefault("speedStatMonster", "RANDOMIZE_BOUNDS"));
    }
}
