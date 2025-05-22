using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;
using StardewValley.Menus;
using StardewValley.BellsAndWhistles;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.Collections.Generic;

namespace Notes
{
    public class ModEntry : Mod
    {
        private List<string> savedNotes = new();

        public override void Entry(IModHelper helper)
        {
            helper.Events.Input.ButtonPressed += OnButtonPressed;
            this.Monitor.Log("Notes mod loaded.", LogLevel.Info);
        }

        private void OnButtonPressed(object? sender, ButtonPressedEventArgs e)
        {
            if (!Context.IsWorldReady)
                return;

            // Press N to view notes
            if (e.Button == SButton.N)
            {
                this.Monitor.Log(" Opening Notes menu.", LogLevel.Debug);
                Game1.activeClickableMenu = new NotesMenu(savedNotes);
            }

            // Press / to write a new note
            if (e.Button == SButton.OemQuestion) 
            {
                this.Monitor.Log(" Opening note input box.", LogLevel.Debug);
                Game1.activeClickableMenu = new NamingMenu(OnNoteEntered, "Enter your note:", "");
            }
        }

        private void OnNoteEntered(string noteText)
        {
            if (string.IsNullOrWhiteSpace(noteText))
            {
                Game1.addHUDMessage(new HUDMessage(" Note was empty. Nothing saved.", 3));
                return;
            }

            savedNotes.Add(noteText);
            Game1.addHUDMessage(new HUDMessage($" Note saved: \"{noteText}\"", 2));
            this.Monitor.Log($" Saved note: {noteText}", LogLevel.Info);
        }

        private class NotesMenu : IClickableMenu
        {
            private readonly List<string> notes;
            private readonly int lineHeight = 40;

            public NotesMenu(List<string> notes)
                : base(Game1.viewport.Width / 2 - 300, Game1.viewport.Height / 2 - 200, 600, 400, true)
            {
                this.notes = notes;
            }

            public override void draw(SpriteBatch b)
            {
                Game1.drawDialogueBox(this.xPositionOnScreen, this.yPositionOnScreen, this.width, this.height, false, true);
                SpriteText.drawString(b, "Your Notes", this.xPositionOnScreen + 20, this.yPositionOnScreen + 20);

                for (int i = 0; i < notes.Count; i++)
                {
                    string note = notes[i];
                    int y = this.yPositionOnScreen + 60 + i * lineHeight;

                    if (y + lineHeight < this.yPositionOnScreen + this.height - 20)
                        SpriteText.drawString(b, $"- {note}", this.xPositionOnScreen + 40, y);
                }

                base.draw(b);
                this.drawMouse(b);
            }
        }
    }
}
