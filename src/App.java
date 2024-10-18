import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);  
        System.out.println("What's your name?");
        String userInput = myObj.nextLine(); 
        String nameResponse = NameQuestion(userInput);
        System.out.println(nameResponse);
        speak(nameResponse);


        System.out.println("What's your favorite sport?");
        userInput = myObj.nextLine(); 
        String sportsResponse = SportQuestion(userInput);
        System.out.println(sportsResponse);
        speak(sportsResponse);
        }

    static String NameQuestion(String UserInput){
            return "Hello " + UserInput + "!";
    }

    static String SportQuestion(String UserInput){
    if (UserInput.equalsIgnoreCase("Golf")){
        return UserInput + " is a great sport!";
    } 
    else if (UserInput.equalsIgnoreCase("Basketball") || UserInput.equalsIgnoreCase("Soccer") || UserInput.equalsIgnoreCase("Football")) {
        return UserInput + " is a common sport.";
    }
    else if (UserInput.equalsIgnoreCase("Baseball") || UserInput.equalsIgnoreCase("Swimming")) {
        return UserInput + " is a cool sport.";
    }

    else{
        return UserInput + " is a sport I have not heard of!";
    }
    }

    public static void speak(String text) {
        try {
            String[] command = {"say", text};  
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();


            process.waitFor(); // wait for the process to finish
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
}
}

/*
Test Cases
- Diff result 1: Any Name, Golf
- Diff result 2: Any Name, Basketball 
- Diff result 2: Any Name, Soccer
- Diff result 2: Any Name, Football
- Diff result 3: Any Name, Baseball
- Diff result 3: Any Name, Swimming
 */

 /*
resource
- https://stackoverflow.com/questions/55633912/running-a-command-in-macos-terminal-from-java
- https://stackoverflow.com/questions/62329459/how-to-have-java-run-terminal-commands-on-mac-echo-command
- https://www.baeldung.com/java-lang-processbuilder-api
  */

