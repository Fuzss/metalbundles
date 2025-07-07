#!/usr/bin/env python3
import json
import os
from pathlib import Path

# ─── CONFIGURATION ─────────────────────────────────────────────────────────────

# Where to write your JSON files
OUT_DIR = Path("Common/src/main/resources/data/metalbundles/item_contents_provider")
OUT_DIR.mkdir(parents=True, exist_ok=True)

# All your bundle tiers
BUNDLE_TIERS = ["leather", "copper", "iron", "golden", "diamond", "netherite"]

# Dye → hex‐color mapping (vanilla dye colors)
DYE_COLORS = {
    "white":     "#F9FFFE",
    "orange":    "#F9801D",
    "magenta":   "#C74EBD",
    "light_blue":"#3AB3DA",
    "yellow":    "#FED83D",
    "lime":      "#80C71F",
    "pink":      "#F38BAA",
    "gray":      "#474F52",
    "light_gray":"#9D9D97",
    "cyan":      "#169C9C",
    "purple":    "#8932B8",
    "blue":      "#3C44AA",
    "brown":     "#835432",
    "green":     "#5E7C16",
    "red":       "#B02E26",
    "black":     "#1D1D21",
}

# Template keys that stay the same
PROVIDER_TYPE = "metalbundles:metal_bundle"
DISALLOWED = []

# ─── GENERATION ────────────────────────────────────────────────────────────────

for dye, hexcol in DYE_COLORS.items():
    for tier in BUNDLE_TIERS:
        # Skip the base (undyed) items; we only want dyed variants:
        filename = f"{dye}_{tier}_bundle.json"
        content = {
            "type": PROVIDER_TYPE,
            "background_color": hexcol,
            "bundle_type": tier,
            "disallowed_item_contents": DISALLOWED,
            "supported_items": f"metalbundles:{dye}_{tier}_bundle"
        }
        out_path = OUT_DIR / filename
        with open(out_path, "w", encoding="utf-8") as f:
            json.dump(content, f, indent=2)
        print(f"Wrote {out_path}")

print("✅ All dyed‐bundle provider files generated.")
