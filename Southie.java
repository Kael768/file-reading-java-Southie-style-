import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;//to make a new output file
import java.util.Scanner;

public class Southie {

    public static void main(String[] args) throws FileNotFoundException {//required

        Scanner input = new Scanner(new File("jaws.txt")); // call scanner to scan jaws.txt as readLine does to a file in python
        PrintStream output = new PrintStream("output.txt"); //this will make a new file instead of running it in the terminal

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String converted = convertLine(line);

            output.println(converted);//result is the converted file from convertLine
            // System.out.println(converted); // optional debugging(this print converted to terminal)
        }

        input.close();//same too as python
        output.close();
    }

    // Converts an entire line
    public static String convertLine(String line) {
        Scanner scanner = new Scanner(line);
        StringBuilder result = new StringBuilder();

        while (scanner.hasNext()) {
            String word = scanner.next();
            result.append(convertWord(word)).append(" ");
        }

        scanner.close();
        return result.toString().trim();//trim includes tab, spaces, next line in a file and doesn't ignore it
    }

    // Converts a single word
    public static String convertWord(String word) {//function for wording and letter replacement in the file
        word = replaceVery(word);
        word = addRAfterA(word);
        word = applyRRules(word);
        return word;
    }

    // Rule 3: Replace "very" with "wicked"
    public static String replaceVery(String word) {
        if (word.equalsIgnoreCase("very")) {
            return "wicked";
        }
        return word;
    }

    // Rule 2: If word ends in 'a' (but not "a"), append 'r'
    public static String addRAfterA(String word) {
        if (word.length() > 1 &&word.toLowerCase().endsWith("a") &&!word.equalsIgnoreCase("a")) {// the ignoreCase thing is for double aa to aar a to none, ar to ar
            return word + "r";
        }
        return word;
    }

    // Rule 1 + Exceptions
    public static String applyRRules(String word) {

        String lower = word.toLowerCase();

        // Exception: ends in "oor"
        if (lower.endsWith("oor")) {
            return word.substring(0, word.length() - 1) + "wah";
        }

        // Exception: ends in "eer"
        if (lower.endsWith("eer")) {
            return word.substring(0, word.length() - 1) + "yah";
        }

        // Exception: ends in vowel + r (specifically i + r)
        if (lower.length() > 1 && lower.endsWith("r") && lower.charAt(lower.length() - 2) == 'i') {
            return word.substring(0, word.length() - 1) + "yah";
        }

        // Basic rule: r after vowel → h
        StringBuilder builder = new StringBuilder(word);

        for (int i = 1; i < builder.length(); i++) {
            char current = Character.toLowerCase(builder.charAt(i));
            char previous = Character.toLowerCase(builder.charAt(i - 1));

            if (current == 'r' && isVowel(previous)) {
                builder.setCharAt(i, 'h');
            }
        }
        return builder.toString();
    }

    public static boolean isVowel(char c) {
        return "aeiou".contains(Character.toString(Character.toLowerCase(c)));
    }
}
//don't bother asking me more
//do it yourself