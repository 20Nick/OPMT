package one.scarecrow.games.OPMT.Game;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    private void randomAi() {
        List<Integer> moves = returnValidMoves();
        Random rand = new Random();

        int randNumber = rand.nextInt(moves.size());

        //randNumber = 0;
        String print = "";
        for (int s: moves) {
            print = print + " " + s;
        }

        Log.d("Random Ai", "List of moves are : " + print);

        Log.d("Random Ai", "Random number is " + randNumber + " :: move list size is "+ moves.size());
        Log.d("Random Ai", "Moves :: " + moves.get(randNumber));

        pieces.select(moves.get(randNumber));


    }

    private List<Integer> returnValidMoves(){
        int[] l;
        List<Integer> moves = new ArrayList();

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
