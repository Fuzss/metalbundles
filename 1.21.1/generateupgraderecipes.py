import json
from pathlib import Path

# Configuration
OUT_DIR = Path("Common/src/main/resources/data/metalbundles/recipe")
OUT_DIR.mkdir(parents=True, exist_ok=True)

# Define tiers and their upgrade materials
upgrade_mapping = {
    "leather":   ("copper",   "minecraft:copper_ingot"),
    "copper":    ("iron",     "minecraft:iron_ingot"),
    "iron":      ("golden",   "minecraft:gold_ingot"),
    "golden":    ("diamond",  "minecraft:diamond"),
    "diamond":   ("netherite","minecraft:netherite_ingot")
}

# Dye colors (must match your tags and item IDs)
DYE_COLORS = [
    "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink",
    "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
]

# Template for the recipe JSON
def make_recipe(dye: str, from_tier: str, to_tier: str, material: str):
    return {
        "type": "metalbundles:copy_components_shaped_recipe",
        "pattern": [
            " # ",
            "#X#",
            " # "
        ],
        "key": {
            "#": {"item": material},
            "X": {"tag": f"metalbundles:{from_tier}_bundles"}
        },
        "result": {
            "item": f"metalbundles:{dye}_{to_tier}_bundle"
        },
        "copy_from": "X"
    }

# Generate one recipe per dye per upgrade step
for from_tier, (to_tier, material) in upgrade_mapping.items():
    for dye in DYE_COLORS:
        # Skip undyed ("" dye)
        recipe = make_recipe(dye, from_tier, to_tier, material)
        filename = f"{dye}_{from_tier}_to_{to_tier}_bundle.json"
        with open(OUT_DIR / filename, "w", encoding="utf-8") as f:
            json.dump(recipe, f, indent=2)
        print(f"Created {filename}")

print("✅ Upgrade recipes generated.")
