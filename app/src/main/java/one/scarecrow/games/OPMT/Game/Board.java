package one.scarecrow.games.OPMT.Game;

import android.util.Log;

public class Board {

    String currentTurn = "black";
    Pieces pieces = new Pieces();

    public Board() {
        //Init board
        pieces.resetPieces();
    }

    /**
     *  The first this that runs after a button press or ai move.
     *
     * @param buttonName Button name is the number for the button (1-9)
     * @return the pieces instant.
     */
    public Pieces runTurn(int buttonName){
        //Check if there is anything selected
        if(pieces.isAnySelected()){
            int selectedPiece = pieces.getSelectedPiece();
            //Check if the piece is not the same (Don't run if Double click) and if it is a valid move
            if (selectedPiece != buttonName && isValidMove(selectedPiece, pieces.getPieceType(selectedPiece), pieces.findEmptySpace()) && isCurrentTurn(selectedPiece)) {
                //Move only to empty space
                if (buttonName == pieces.findEmptySpace()){
                    pieces.movePiece(buttonName);
                    //Switch turn
                    switchTurn();
                }
            }
            //Unselects the piece
            pieces.unselect(buttonName);
        }else if (isValidMove(buttonName, pieces.getPieceType(buttonName), pieces.findEmptySpace()) && isCurrentTurn(buttonName)){
            pieces.select(buttonName);
        }
        return pieces;

    }

    /**
     * @return pieces
     */
    public Pieces getPieces(){
        return pieces;
    }

    /**
     * Checks if there is any valid moves that current player can do
     * Need to fix this
     */
    public boolean isWin(Pieces p) {
        int[] l;

        if(currentTurn.equals("black")){
            l = p.findAllBlackPieces();
        }else {
            l =  p.findAllWhitePieces();
        }

        for (int i = 1; i < l.length; i++){
            if(isValidMove(l[i], p.getPieceType(l[i]), p.findEmptySpace())){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * if( Switches the turn, should be used at the end of a turn.
     */
    public void switchTurn() {
        if(currentTurn.equals("black")){
            currentTurn = "white";
        }else if(currentTurn.equals("white")){
            currentTurn = "black";
        }
    }

    /**
     * Checks if the current piece is the same color as current turn
     *
     * @param buttonName the piece id being checked
     * @return true if it is the pieces current turn.
     */
    private boolean isCurrentTurn(int buttonName) {
        if(pieces.getPieceType(buttonName).contains(currentTurn)){
            return true;
        }
        return false;
    }

    /**
     * Gets the current turn
     *
     * @param opposite if turn will return who's turn it is not
     * @return returns white or black
     */
    public String getCurrentTurn(boolean opposite){
        if (opposite){
            if (currentTurn.equals("black")){
                return "white";
            }else {
                return "black";
            }
        }
        return currentTurn;
    }

    /**
     * Gets the current turn
     *
     * @return returns white or black
     */
    public String getCurrentTurn(){
        return currentTurn;
    }

    /**
     * Checks if a piece is able to move.
     *
     * @param from The id of the piece in question to check if it is allowed to move to the empty space.
     * @return true if the piece is able to move.
     */
    public boolean isValidMove(int from, String fromType, int emptySpace){

        // Cannot move if piece is next to each other

        if (validMoveAdjacencyCheck(from,  fromType, 8, 1, 2, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 1, 2, 3, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 2, 3, 4, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 3, 4, 5, emptySpace) ) {
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 3, 4, 5, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 4, 5, 6, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 5, 6, 7, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 6, 7, 8, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, fromType, 7, 8, 1, emptySpace)){
            return true;
        }

        //From the middle spot, you can move anywhere
        return from == 9;
    }


    /**
     *  This function should be used with the isValidMove method. This checks adjacency for to see if you are able to move
     * @param from the piece that is being checked to see if valid move
     * @param n1 the piece id to the left of from
     * @param c is the number meant to go between n1 and n2.
     * @param n2 the piece id to the right of from
     * @param emptySpace piece of where the empty space is
     * @return true if all checks are clear.
     */                                      //   1       8        1     2
    private boolean validMoveAdjacencyCheck(int from, String fromType, int n1, int c, int n2, int emptySpace){
        if ((from == c && isColor(n1, fromType) && isColor(n2, fromType))) {
            return false;
        }

        return validMoveEmptySpaceCheck(from, n1, c, n2, emptySpace);
    }

    private boolean validMoveEmptySpaceCheck(int from, int n1, int c, int n2, int emptySpace){
        return from == c && (emptySpace == n1 || emptySpace == n2 || emptySpace == 9);
    }

    /**
     * Checks if piece is a color
     *
     * @param piece the id of the piece (1-9)
     * @param color can be black or white
     * @return returns true if the piece is the specified color.
     */
    public boolean isColor(int piece, String color){
        return pieces.getPieceType(piece).contains(color);
    }

}
