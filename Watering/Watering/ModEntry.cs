using Microsoft.Xna.Framework;
using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;
using StardewValley.TerrainFeatures;
using System.Collections.Generic;

namespace Watering
{
    /// <summary>The mod entry point.</summary>
    internal sealed class MonEntry : Mod
    {
        /*********
        ** Public methods
        *********/
        /// <summary>The mod entry point, called after the mod is first loaded.</summary>
        /// <param name="helper">Provides simplified APIs for writing mods.</param>
        public override void Entry(IModHelper helper)
        {
            helper.Events.Input.ButtonPressed += this.OnButtonPressed;
        }


          /*********
          ** Private methods
          *********/
          /// <summary>Raised after the player presses a button on the keyboard, controller, or mouse.</summary>
          /// <param name="sender">The event sender.</param>
          /// <param name="e">The event data.</param>
          private void OnButtonPressed(object? sender, ButtonPressedEventArgs e)
          {

               // ignore if player is in a cutscene or paused, only play on right mouse
               if (!Context.IsPlayerFree || e.Button != SButton.MouseRight)
                    return;

               // get tile where player clicked 
               // https://stardewvalleywiki.com/Modding:Farm_data
               // https://github.com/spacechase0/StardewValleyMods/blob/develop/ContentCode/docs/author-guide.md 
               // https://chatgpt.com/share/6826367c-6c94-800f-9a2a-997fe1537b1f 
          
               Vector2 clickTile = Game1.currentCursorTile;

               float radius = 4f;

               int wateredCounter = 0;

               // loop through terrain features 
               foreach (var pair in Game1.currentLocation.terrainFeatures.Pairs)
               {
                    Vector2 tile = pair.Key;
                    TerrainFeature feature = pair.Value;

                    if (feature is HoeDirt dirt)
                    {
                         float dist = Vector2.Distance(tile, clickTile);

                         if (dist <= radius)
                         {
                         dirt.state.Value = HoeDirt.watered;
                         wateredCounter++;
                         }
                    }
               }

               Monitor.Log($"Watered {wateredCounter} crops within radius {radius} of tile {clickTile}.", LogLevel.Info); 


               
        }
    }
}