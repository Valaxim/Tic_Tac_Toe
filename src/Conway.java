public class Conway {
    public static void main(String[] args) {
        GreetingInfo();
        Board k = new Board();
        k.run();
    }

    static void GreetingInfo() {
        System.out.println("Witaj!  TIC TAC TOE\n");
        System.out.println("Spis dostępnych opcji:\n");
        System.out.print("0/ SINGLEPLAYER \n1/ MULTIPLAYER \nPozostałe Integery: SINGLEPLAYER\n");
    }
}

