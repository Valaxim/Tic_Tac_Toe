public class AI  {

    private Board board;

    public AI(Board board) {
        this.board = board;
    }
    
    
    
    public void makeAIFirstMove() {
        if (board.getTab().get(1, 1).equals(XO.EMPTY))
            board.getTab().set(XO.O, 1, 1);
        else
            board.getTab().set(XO.O, 0, 0);
    }

    public void makeAISecondMove() {
        if (!checkEnemyTwoInRow())
            if (!avoidAmbush())
                setAnywhere();
    }

    public void makeAIThirdMove() {
        if (!checkMyTwoInRow()) {
            if (!checkEnemyTwoInRow())
                setAnywhere();
        }
    }

    public void makeAIFourthMove() {
        if (!checkMyTwoInRow()) {
            if (!checkEnemyTwoInRow())
                setAnywhere();
        }
    }

    // WARUNKI RUCHÓW AI

    public boolean checkEnemyTwoInRow() {
        boolean possibleMove = false;
        int a = 0, b = 0;
        for (int i = 0; i < board.getROWS(); i++) {
            for (int j = 0; j < board.getCOLS(); j++) {
                if (board.getTab().get(i, j).equals(XO.EMPTY)) {
                    board.getTab().set(XO.X, i, j);
                    if (board.checkWinCondition()) {
                        possibleMove = true;
                        a = i;
                        b = j;
                    }
                    board.getTab().set(XO.EMPTY, i, j);
                }
            }
        }
        if (possibleMove)
            board.getTab().set(XO.O, a, b);
        return possibleMove;
    }

    public boolean checkMyTwoInRow() {
        boolean possibleMove = false;
        int a = 0, b = 0;
        for (int i = 0; i < board.getROWS(); i++) {
            for (int j = 0; j < board.getCOLS(); j++) {
                if (board.getTab().get(i, j).equals(XO.EMPTY)) {
                    board.getTab().set(XO.O, i, j);
                    if (board.checkDefeatCondition()) {
                        possibleMove = true;
                        a = i;
                        b = j;
                    }
                    board.getTab().set(XO.EMPTY, i, j);
                }
            }
        }
        if (possibleMove)
            board.getTab().set(XO.O, a, b);
        return possibleMove;
    }

    public boolean avoidAmbush() {
        if (board.getTab().get(0, 0).equals(XO.X) && board.getTab().get(1, 1).equals(XO.O) && board.getTab().get(2, 2).equals(XO.X)) {
            board.getTab().set(XO.O, 0, 1);
            return true;
        }
        if (board.getTab().get(0, 2).equals(XO.X) && board.getTab().get(1, 1).equals(XO.O) && board.getTab().get(2, 0).equals(XO.X)) {
            board.getTab().set(XO.O, 0, 1);
            return true;
        }
        return false;
    }

    public void setAnywhere() {
        for (int i = 0; i < board.getROWS(); i++)
            for (int j = 0; j < board.getCOLS(); j++)
                if (board.getTab().get(i, j).equals(XO.EMPTY)) {
                    board.getTab().set(XO.O, i, j);
                    j = board.getROWS();
                    i = board.getCOLS();
                }
    }





}
