Minecraft plugin that lets you add custom recipies.

This plugin is intended to be used with any gui plugin that supports running commands as player!

Crafting command : /itemcraft <name>

Example config (not included in the plugin)
```
#add recipe names here
craftItems:
- plank
# add recipes for items here:
# Supports:
# Material:
# Amount: 1-64
# ItemMeta:
#    - DisplayName: name your item will have, use § for color codes
#    - CustomModelData: 1-9
#    - Lore:
#        - 'line1'
#        - 'line2'
#        - 'line3'
#        - 'line4'
#        - "use § for color codes"
#    -  unbreakable: true/false
plank:
  craft:
    Material: OAK_PLANKS
    Amount: 1
  ingredients:
    '1':
      Material: STICK
      Amount: 16
```
another item example :
```
wooden_axe:
  craft:
    Material: WOODEN_AXE
    Amount: 1
    ItemMeta:
      DisplayName: §7Magical Wooden Axe
      CustomModelData: 1
      Lore:
      - §8[§6Legendary§8]
      - ''
      unbreakable: true
  ingredients:
    '1':
      Material: STRING
      Amount: 1
      ItemMeta:
        DisplayName: §7Magic String
        CustomModelData: 1
        Lore:
        - §8text
        - ''

    '2':
      Material: STICK
      Amount: 1
      ItemMeta:
        DisplayName: §6THE STICK
        CustomModelData: 7
        Lore:
        - §d§klore
        - ''
        - §7lore
        - §7lore
```
