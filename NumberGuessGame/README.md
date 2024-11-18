## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Reflection 

a short reflection (2-3 sentences) on whether or not you used recursion (why or why not), what you had trouble with, how you overcame those troubles, and what would you do more if you had more time. 

I didn't use recursion because the code for iterative loops was easier to follow and manage. One challenge was making sure all the errors and exceptions were caught, especially when handling non-integer inputs. I would create Games in seperate classes and create a final score after multiple rounds. 

## Updated Reflection (After bonus) 
For the extra credit, I learned about Classes and created multiple rounds for the game. I also had to look at public functions to put the game play in one function. I had trouble with understanding classes and ensuring that the functions did what I wanted. The code got a bit messy after a while and I had to sort through my functions and made sure all the test cases were accounted for. The logic for the text cases went through multiple interations before getting to the form it is today. I struggled a bit with understanding the logic for text cases and not having conflicts. In the future, I would like to try the hangman game. 

## Test Cases 

### Case 1: Negative Range 

```  
Enter a positive integer range for the game:
-4
Enter a positive integer for the range:
```

There isn't a specific error message, but the game does not take the negative integer 

### Case 2: Not integer range 

```
Enter a positive integer for the range:
0.04
That's not an integer. Try again:
```
The game returns an error message to the user to try again 

### Case 3: Positive Integer Range 

```
Enter a positive integer for the range:
9

Starting Game 1 of 3:
The computer has chosen a number. Start Guessing!
Enter your guess:
```
The game takes the positive integer range and tells the user the computer has a random number and to start guessing. 

### Case 4: Guess isn't in range 

``` 
That's not an integer. Try again:
9

Starting Game 1 of 3:
The computer has chosen a number. Start Guessing!
Enter your guess: 10
Guess out of range. Try a number between 0 and 9
Enter your guess:
```

The game returns an error message and tells the user to try again. The guess is not counted. 

### Case 5: Guess not an integer 

```
Enter your guess: 0.04
That's not a number. Please enter an integer:
Enter your guess:
```

The game returns an error message and makes the user try again. The guess is not counted. 

### Case 6: Guess in range 

```
The computer has chosen a number. Start Guessing!
Enter your guess: 6
The number is larger than 6
Enter your guess: 3
The number is larger than 3
Enter your guess: 9
The number is smaller than 9
Enter your guess: 8
The number is smaller than 8
Enter your guess: 7
Correct. You got it in 5 guesses.

Starting Game 2 of 3:
The computer has chosen a number. Start Guessing!
Enter your guess: 
```

The game tells you if your guess is larger or smaller and tells you you are correct when you get the number. It also tells you how many times you guessed. It then starts the next game as Game 2 out of 3. After you finish Game 2, the game moves to Game 3. When you guess the number in Game 3, it reveals the lowest number of guesses. 

```
Enter a positive integer range for the game:
10

Starting Game 1 of 3:
The computer has chosen a number. Start Guessing!
Enter your guess: 6
The number is larger than 6
Enter your guess: 3
The number is larger than 3
Enter your guess: 9
The number is smaller than 9
Enter your guess: 8
The number is smaller than 8
Enter your guess: 7
Correct. You got it in 5 guesses.

Starting Game 2 of 3:
The computer has chosen a number. Start Guessing!
Enter your guess: 5
The number is smaller than 5
Enter your guess: 3
The number is smaller than 3
Enter your guess: 2
Correct. You got it in 3 guesses.

Starting Game 3 of 3:
The computer has chosen a number. Start Guessing!
Enter your guess: 4
The number is larger than 4
Enter your guess: 5
The number is larger than 5
Enter your guess: 6
Correct. You got it in 3 guesses.
The best game score was: 3 guesses.
```



