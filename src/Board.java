import static java.lang.System.exit;

public class Board {
    private Matrix<XO> tab;
    static final int ROWS = 3;
    static final int COLS = 3;

    public static int getCOLS() {
        return COLS;
    }

    public static int getROWS() {
        return ROWS;
    }

    public Matrix<XO> getTab() {
        return tab;
    }

    public void setTab(Matrix<XO> tab) {
        this.tab = tab;
    }


    public Board() {
        tab = new Matrix<>(ROWS, COLS);
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                tab.set(XO.EMPTY, i, j);
    }

    public void show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                sb.append(tab.get(i, j));
                if (j == 0 || j == 1)
                    sb.append("|");
            }
            if (i == 0 || i == 1)
                sb.append("\n-----------\n");
        }
        System.out.println(sb);
    }

    public boolean checkAnyWinCondition() {
        return (checkWinCondition() || checkDefeatCondition());
    }

    public boolean checkWinCondition() {
        return condition(XO.X);
    }

    public boolean checkDefeatCondition() {
        return condition(XO.O);
    }

    public boolean condition(XO a) {
        if (tab.get(0, 0).equals(a) && tab.get(1, 1).equals(a) && tab.get(2, 2).equals(a))  // przekatna
            return true;
        if (tab.get(0, 2).equals(a) && tab.get(1, 1).equals(a) && tab.get(2, 0).equals(a))
            return true;
        if (tab.get(0, 0).equals(a) && tab.get(0, 1).equals(a) && tab.get(0, 2).equals(a))  // w poziomie
            return true;
        if (tab.get(1, 0).equals(a) && tab.get(1, 1).equals(a) && tab.get(1, 2).equals(a))
            return true;
        if (tab.get(2, 0).equals(a) && tab.get(2, 1).equals(a) && tab.get(2, 2).equals(a))
            return true;
        if (tab.get(0, 0).equals(a) && tab.get(1, 0).equals(a) && tab.get(2, 0).equals(a)) // w pionie
            return true;
        if (tab.get(0, 1).equals(a) && tab.get(1, 1).equals(a) && tab.get(2, 1).equals(a))
            return true;
        if (tab.get(0, 2).equals(a) && tab.get(1, 2).equals(a) && tab.get(2, 2).equals(a))
            return true;
        return false;
    }

    public boolean gameNotFinished() {
        return (!checkAnyWinCondition() && avalibleMove() != 0);

    }

    public void endOfTheGame() {
        if (checkAnyWinCondition()) {
            if (checkWinCondition())
                System.out.println("WYGRAL X !!!! ");
            else
                System.out.println("WYGRAL O !!!! ");
            show();
            exit(0);
        }

    }

    public void run() {
        Mode[] arrayOfMode = Mode.values();
        Mode switchChoice;
        int mode = ReadInt.createIntegerNumber();

        try {
            switchChoice = arrayOfMode[mode];
        } catch (ArrayIndexOutOfBoundsException e) {
            switchChoice = Mode.SINGLE;
        }

        switch (switchChoice) {     // przekaż do Board::run(mode)
            case SINGLE:
                runSingleplayer();
                break;
            case MULTI:
                runMultiplayer();
                break;
        }
    }

    public void runMultiplayer() {
        int[] input;
        int i = 0;
        while (gameNotFinished()) {
            System.out.println("Aktualny stan rozgrywki");
            show();
            avalibleMove();
            if (i % 2 == 0) {
                input = getNumber();
                i += makePcXMove(input);
            } else {
                input = getNumber();
                i += makePcOMove(input);
            }
            endOfTheGame();
        }
        System.out.println("REMIS");
    }


    public int avalibleMove() {
        StringBuilder sb = new StringBuilder();
        int numberOfMoves = 0;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                if (tab.get(i, j).equals(XO.EMPTY)) {
                    sb.append("(").append(i).append(" ").append(j).append(")  ");
                    numberOfMoves++;
                }
        System.out.println("Możliwe ruchy: " + sb);
        return numberOfMoves;
    }


    public int makePcXMove(int[] a) {
        if (tab.get(a[0], a[1]).equals(XO.EMPTY)) {
            tab.set(XO.X, a[0], a[1]);
            return 1;
        } else {
            System.out.println("RUCH NIEDOZWOLONY");
            return 0;
        }
    }


    public int makePcOMove(int[] a) {
        if (tab.get(a[0], a[1]).equals(XO.EMPTY)) {
            tab.set(XO.O, a[0], a[1]);
            return 1;
        } else {
            System.out.println("RUCH NIEDOZWOLONY");
            return 0;
        }
    }


    public int[] getNumber() {
        System.out.println("Podaj 2 liczby: [0-2] //ENTER// [0-2] //ENTER//");
        int[] input = new int[]{5, 5};
        boolean valid = false;
        do {
            System.out.println("Wprowadź pierwszą liczbę ");
            input[0] = ReadInt.createIntegerNumber();
            System.out.println("Wprowadź drugą liczbę ");
            input[1] = ReadInt.createIntegerNumber();
            if (!(input[0] > 2 || input[1] > 2))
                valid = true;
            else
                System.out.println("NIEPOWODZENIE !! Wprowadź liczby z zakresu 0 - 2");
        } while (!valid);
        return input;
    }


    public void runSingleplayer() {
        AI ai = new AI(this);
        int[] input;
        int i = 0;
       // AI k = new AI();
        while (gameNotFinished()) {
            System.out.println("Aktualny stan rozgrywki");
            show();
            avalibleMove();
            // System.out.println("NR ITERACJII" + i);
            if (i % 2 == 0) {
                input = getNumber();
                i += makePcXMove(input);
            } else if (i == 1) {
                ai.makeAIFirstMove();
                i++;
            } else if (i == 3) {
                System.out.println("KOMP ROBI 2 RUCH");
                ai.makeAISecondMove();
                i++;
            } else if (i == 5) {
                ai.makeAIThirdMove();
                i++;
            } else if (i == 7) {
                ai.makeAIFourthMove();
                i++;
            }
            endOfTheGame();
        }
        System.out.println("REMIS");
    }

}
