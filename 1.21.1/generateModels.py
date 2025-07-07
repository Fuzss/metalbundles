import os
import json

def generate_bundle_models():
    """
    Generates model JSON files for dyed metal bundles (unfilled and filled)
    into Common/src/generated/resources/assets/metalbundles/models/item
    """
    colors = [
        "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink",
        "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"
    ]
    tiers = ["leather", "copper", "iron", "golden", "diamond", "netherite"]

    # Base directory relative to the script location
    script_dir = os.path.dirname(os.path.abspath(__file__))
    base_dir = os.path.join(
        script_dir,
        "Common", "src", "generated", "resources",
        "assets", "metalbundles", "models", "item"
    )
    os.makedirs(base_dir, exist_ok=True)

    count = 0
    for color in colors:
        for tier in tiers:
            base_name = f"{color}_{tier}_bundle"

            # build textures dict, conditionally adding layer1
            unfilled_textures = {
                "layer0": f"metalbundles:item/{color}_bundle"
            }
            if tier != "leather":
                unfilled_textures["layer1"] = f"metalbundles:item/{tier}_bundle_open_string"

            # Unfilled model JSON
            unfilled = {
                "parent": "minecraft:item/generated",
                "overrides": [
                    {
                        "model": f"metalbundles:item/{base_name}_filled",
                        "predicate": {"metalbundles:filled": 1.0}
                    }
                ],
                "textures": unfilled_textures
            }
            unfilled_path = os.path.join(base_dir, f"{base_name}.json")
            with open(unfilled_path, "w", encoding="utf-8") as f:
                json.dump(unfilled, f, indent=2)
            count += 1

            # build textures for filled, conditionally adding layer1
            filled_textures = {
                "layer0": f"metalbundles:item/{color}_bundle_filled"
            }
            if tier != "leather":
                filled_textures["layer1"] = f"metalbundles:item/{tier}_bundle_string"

            # Filled model JSON
            filled = {
                "parent": "minecraft:item/generated",
                "textures": filled_textures
            }
            filled_path = os.path.join(base_dir, f"{base_name}_filled.json")
            with open(filled_path, "w", encoding="utf-8") as f:
                json.dump(filled, f, indent=2)
            count += 1

    print(f"Generated {count} model files in {base_dir}")

if __name__ == "__main__":
    generate_bundle_models()
