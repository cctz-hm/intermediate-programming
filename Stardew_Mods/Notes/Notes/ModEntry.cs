using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;
using StardewValley.Menus;
using Microsoft.Xna.Framework;
using StardewValley.Menus;
using System;

namespace Notes
{
     /// <summary>The mod entry point.</summary>
     internal sealed class MonEntry : Mod

     {
          private Item? selectedItemForNote;
          private int? selectedSlotIndex = null;
          private ItemGrabMenu? sourceMenu = null;

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
               // only act on right-click
               if (e.Button != SButton.MouseRight)
                    return;

               // be in an inventory menu
               if (Game1.activeClickableMenu is not ItemGrabMenu menu)
                    return;

               // check if the click was on an inventory slot
               var inventoryMenu = menu.inventory;
               int mouseX = Game1.getMouseX();
               int mouseY = Game1.getMouseY();

               int index = 0;
               foreach (ClickableComponent slot in inventoryMenu.inventory)
               {
               if (slot.containsPoint(mouseX, mouseY))
               {
                    Item? clickedItem = inventoryMenu.getItemAt(slot.bounds.X, slot.bounds.Y);
                    if (clickedItem != null)
                    {
                         this.selectedSlotIndex = index;
                         this.sourceMenu = menu;

                         Game1.activeClickableMenu = new NamingMenu(OnNoteEntered, "Enter Note:", "");
                         return;
                    }
               }
               index++;
               }

               }

          private void OnNoteEntered(string noteText)
          {
          if (string.IsNullOrWhiteSpace(noteText) || selectedSlotIndex == null || sourceMenu == null)
               return;

          var inventoryMenu = sourceMenu.inventory;
          Item? item = inventoryMenu.actualInventory.ElementAtOrDefault(selectedSlotIndex.Value);

          if (item == null)
          {
               this.Monitor.Log("Failed to re-find item after note entry.", LogLevel.Warn);
               return;
          }

          item.modData["cctz_hm.note"] = noteText;

          Game1.addHUDMessage(new HUDMessage($"Note saved: \"{noteText}\"", 2));
          this.Monitor.Log($"Saved note for {item.Name}: {noteText}", LogLevel.Info);

          selectedSlotIndex = null;
          sourceMenu = null;
          }


     }
}

          
     