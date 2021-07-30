package one.scarecrow.games.OPMT.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ai {

    int difficulty;
    boolean isComputerWhite;

    Board board;
    Pieces pieces;

    /**
     *
     * @param difficulty planning for future. Only difficulty is 0 for random number gen ai.
     */
    public Ai(boolean isComputerWhite,int difficulty){
        this.isComputerWhite = isComputerWhite;
        this.difficulty = difficulty;
    }

    /**
     * Will start to run ai depending on the difficulty that was set. Only 1 ai type right now.
     */
    public void run(Board board, Pieces pieces){
        this.board = board;
        this.pieces = pieces;

        switch (difficulty){
            default:
                randomAi();
        }

        pieces.movePiece(pieces.findEmptySpace());
        pieces.unselect();
        board.switchTurn();
    }


    /**
     * The ai will pick a random valid move that it can take.
     */
    private void randomAi() {
        List<Integer> moves = returnValidMoves();
        Random rand = new Random();

        int randNumber = rand.nextInt(moves.size());

        StringBuilder print = new StringBuilder();
        for (int s: moves) {
            print.append(" ").append(s);
        }

        pieces.select(moves.get(randNumber));


    }

    /**
     * Find all valid moves that the AI is able to take and returns a list.
     *
     * @return a list of all valid moves that the ai is able to take.
     */
    private List<Integer> returnValidMoves(){
        int[] l;
        List<Integer> moves = new ArrayList<>();

        if(!isComputerWhite){
            l = pieces.findAllBlackPieces();
        }else {
            l =  pieces.findAllWhitePieces();
        }

        for (int i = 1; i < l.length; i++){
            if(board.isValidMove(l[i], pieces.getPieceType(l[i]), pieces.findEmptySpace())){
                //Valid move
                moves.add(l[i]);
            }
        }

        return moves;
    }
}
