# Language Switcher Tile
Quick settings tile that allows you to easily change your device language.

<img src="screenshots/tile_clicked.png" width="200px">
<img src="screenshots/tile_long_clicked.png" width="200px">
<img src="screenshots/quick_settings_panel_expanded.png" width="200px">

### Installation

1. Import Language Switcher tile to your app.
2. Use LanguageSwitcherTile.Builder to enable tile with specified configuration. 
3. Install the app on your device.
4. Grant CHANGE_CONFIGURATION permission using following method:

```
adb shell pm grant com.azimo.sendmoney.languagetiletileplugin android.permission.CHANGE_CONFIGURATION
```

### How to use
When language switcher tile is enabled click on it and allow it to modify system settings. Then open the quick settings panel, edit it and drop language switcher tile into it. Once the tile is enabled you can change your device language in two ways:

1. Click language switch tile to select next language from a list of supported languages.
2. Long click language switch tile to open supported languages dialog and select language.

If you attached a warning while enabling tile you should see it displayed in toast message after language selection. Now, you can expand quick settings panel to see selected language shortcut under Language Switcher Tile icon.