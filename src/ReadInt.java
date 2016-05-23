import java.util.Scanner;

enum Mode {
    SINGLE, MULTI
}

enum  XO {
    X, O , EMPTY {
        public String toString() {
            return "   ";
        }
    };
    public String toString() {
        String a = name();
        return " " + a + " ";
    }
}

public class ReadInt {

    static int inputInt;
    static public int createIntegerNumber() {
        boolean correct = false;
        do {
            System.out.println("Podaj liczbę: ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (!(input.equals(""))) {
                input = input.substring(0, 1);
                try {
                    inputInt = Integer.parseInt(input);
                    correct = true;
                } catch (NumberFormatException e) {
                    //System.out.println("ZLAPALEM GO" + parsable);
                    System.out.println("Wprowadzony ciąg nie jest Integerem");
                    correct=false;
                }
            }
        } while (!correct);
        return inputInt;
    }
}