# generate_backport_tags_and_recipes.py

import os
import json

def generate_tags_and_recipes():
    colors = [
        "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink",
        "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
    ]
    tiers = ["leather", "copper", "iron", "golden", "diamond", "netherite"]

    recipes_dir = os.path.join("Common", "src", "main", "resources", "data", "metalbundles", "recipe")
    os.makedirs(recipes_dir, exist_ok=True)

    for color in colors:
        for tier in tiers:
            tag_name = f"{tier}_bundles"

            recipe = {
                "type": "minecraft:crafting_shapeless",
                "ingredients": [
                    {"tag": f"metalbundles:{tag_name}"},
                    {"item": f"minecraft:{color}_dye"}
                ],
                "result": {
                    "id": f"metalbundles:{color}_{tier}_bundle",
                    "count": 1
                }
            }
            filename = f"{color}_{tier}_bundle_from_dye.json"
            with open(os.path.join(recipes_dir, filename), "w", encoding="utf-8") as f:
                json.dump(recipe, f, indent=2)

    print("Generated recipes in main.")

if __name__ == "__main__":
    generate_tags_and_recipes()
