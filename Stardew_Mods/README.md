# Reflection

For this project, I learned C# and UI programming on XNA. 

It was hard to implement the mod and learn the new langauge and I ran into troubles along the way from learning the syntax to not knowing what packages to use. Figuring out how Stardew Valley builds its menus and input fields was also complicated and I spend a lot of time understanding what classes controlled what. 

A major failure was trying to use the in-game chatbox to take user input for notes. It turned out that overriding the chat system in Stardew was complicated. I decided to just create a custom input box using `TextBox`, `IClickableMenu`, and buttons. 

Another challenges was modifying the structure of the mod's UI, figuring out when to use `NamingMenu` and how to make the buttons work. My earlier version didn't compile or didn't respond to user input. 

### Mods Explanation 
**Watering**
- If a user right clicks, then any area that is hoed in a certain radius is watered 

**Teleportation**
- If you click "h" on the keyboard, you are teleported back to the house. 

**Notes**
- click "/" to create note 
- if note is created, HUD message shows "saved: "your note here", if the input is blank message shows "note is empty"
- click "n" to see all stored notes 

### Issues
- some of the keys conflict. if you click certain keys while typing message, other commands will happen

### Resources 

- SMAPI Docs: https://stardewvalleywiki.com/Modding:Modder_Guide
- C#: https://www.w3schools.com/cs/index.php 
- Textbox UI in Stardew: https://github.com/spacechase0/StardewValleyExpanded/blob/develop/Mod/CustomMenus.cs
- https://github.com/Pathoschild
- https://github.com/Annosz/StardewValleyModdingGuide
- https://community.playstarbound.com/threads/creating-a-message-box-prompt.112763/


### Classes / Interfaces I Used
- `IClickableMenu`            
Custom UI menu base
- `TextBox`                   
Built-in text input field
- `ClickableComponent`        
For OK and Cancel buttons
- `Game1.keyboardDispatcher`  
For handling text inputs
- `HUDMessage`               
To show temporary messages to the player
- `Game1.activeClickableMenu`
Used to launch custom menus
- `SButton`      
