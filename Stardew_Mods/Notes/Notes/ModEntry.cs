using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;
using StardewValley.Menus;
using StardewValley.BellsAndWhistles;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
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

            if (e.Button == SButton.N)
            {
                Game1.activeClickableMenu = new NotesMenu(savedNotes);
            }

            if (e.Button == SButton.OemQuestion)
            {
                Game1.activeClickableMenu = new NoteInputMenu(OnNoteEntered);
            }
        }

        private void OnNoteEntered(string noteText)
        {
            if (string.IsNullOrWhiteSpace(noteText))
            {
                Game1.addHUDMessage(new HUDMessage("note is empty", 3));
                return;
            }

            savedNotes.Add(noteText);
            Game1.addHUDMessage(new HUDMessage($"saved: \"{noteText}\"", 2));
            this.Monitor.Log($"saved: {noteText}", LogLevel.Info);
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

        private class NoteInputMenu : IClickableMenu
        {
            private readonly TextBox textBox;
            private readonly ClickableComponent okButton;
            private readonly ClickableComponent cancelButton;
            private readonly System.Action<string> onSubmit;

            public NoteInputMenu(System.Action<string> onSubmit)
                : base(Game1.viewport.Width / 2 - 300, Game1.viewport.Height / 2 - 100, 600, 200, true)
            {
                this.onSubmit = onSubmit;

                textBox = new TextBox(
                    Game1.content.Load<Texture2D>("LooseSprites\\textBox"),
                    Game1.content.Load<Texture2D>("LooseSprites\\textBox"),
                    Game1.dialogueFont,
                    Game1.textColor
                )
                {
                    X = this.xPositionOnScreen + 50,
                    Y = this.yPositionOnScreen + 60,
                    Width = 500,
                    Height = 64,
                    Text = "",
                    Selected = true
                };

                okButton = new ClickableComponent(new Rectangle(this.xPositionOnScreen + 130, this.yPositionOnScreen + 130, 100, 40), "OK");
                cancelButton = new ClickableComponent(new Rectangle(this.xPositionOnScreen + 370, this.yPositionOnScreen + 130, 100, 40), "Cancel");

                Game1.keyboardDispatcher.Subscriber = textBox;
            }

            public override void draw(SpriteBatch b)
            {
                Game1.drawDialogueBox(xPositionOnScreen, yPositionOnScreen, width, height, false, true);
                SpriteText.drawString(b, "Enter your note:", xPositionOnScreen + 50, yPositionOnScreen + 20);
                textBox.Draw(b);

                // OK button
                IClickableMenu.drawTextureBox(
                    b, Game1.mouseCursors, new Rectangle(403, 383, 6, 6),
                    okButton.bounds.X, okButton.bounds.Y, okButton.bounds.Width, okButton.bounds.Height,
                    Color.White, 4f);
                SpriteText.drawString(b, "OK", okButton.bounds.X + 25, okButton.bounds.Y + 10);

                // Cancel button
                IClickableMenu.drawTextureBox(
                    b, Game1.mouseCursors, new Rectangle(403, 383, 6, 6),
                    cancelButton.bounds.X, cancelButton.bounds.Y, cancelButton.bounds.Width, cancelButton.bounds.Height,
                    Color.White, 4f);
                SpriteText.drawString(b, "Cancel", cancelButton.bounds.X + 10, cancelButton.bounds.Y + 10);

                base.draw(b);
                this.drawMouse(b);
            }

            public override void receiveLeftClick(int x, int y, bool playSound = true)
            {
                base.receiveLeftClick(x, y, playSound);

                if (okButton.containsPoint(x, y))
                {
                    Game1.playSound("smallSelect");
                    onSubmit(textBox.Text.Trim());
                    Game1.exitActiveMenu();
                }
                else if (cancelButton.containsPoint(x, y))
                {
                    Game1.playSound("bigDeSelect");
                    Game1.exitActiveMenu();
                }
            }

            public override void receiveKeyPress(Keys key)
            {
                base.receiveKeyPress(key);

                if (key == Keys.Enter)
                {
                    onSubmit(textBox.Text.Trim());
                    Game1.exitActiveMenu();
                }
                else if (key == Keys.Escape)
                {
                    Game1.exitActiveMenu();
                }
            }
        }
    }
}
