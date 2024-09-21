package fuzs.metalbundles.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;

public class ServerConfig implements ConfigCore {
    @Config(description = "Multiplier for the base bundle capacity of 64 items.")
    @Config.IntRange(min = 1)
    public int leatherCapacityMultiplier = 1;
    @Config(description = "Multiplier for the base bundle capacity of 64 items.")
    @Config.IntRange(min = 1)
    public int copperCapacityMultiplier = 2;
    @Config(description = "Multiplier for the base bundle capacity of 64 items.")
    @Config.IntRange(min = 1)
    public int ironCapacityMultiplier = 4;
    @Config(description = "Multiplier for the base bundle capacity of 64 items.")
    @Config.IntRange(min = 1)
    public int goldenCapacityMultiplier = 8;
    @Config(description = "Multiplier for the base bundle capacity of 64 items.")
    @Config.IntRange(min = 1)
    public int diamondCapacityMultiplier = 16;
    @Config(description = "Multiplier for the base bundle capacity of 64 items.")
    @Config.IntRange(min = 1)
    public int netheriteCapacityMultiplier = 32;
}
