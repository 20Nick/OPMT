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
            if (selectedPiece != buttonName && isValidMove(selectedPiece)) {
                //Move only to empty space
                if (buttonName == pieces.findEmptySpace()){
                    pieces.movePiece(buttonName, selectedPiece);
                }
            }
            //Unselects the piece
            pieces.unselect(buttonName);
        }else if (isValidMove(buttonName)){
            pieces.select(buttonName);
        }
        return pieces;

    }

    public String getCurrentTurn(){
        return currentTurn;
    }

    /**
     * Checks if a piece is able to move.
     *
     * @param from The id of the piece in question to check if it is allowed to move to the empty space.
     * @return true if the piece is able to move.
     */
    public boolean isValidMove(int from){
        //Find empty space
        int emptySpace = pieces.findEmptySpace();
        // Cannot move if piece is next to each other

        String fromType = pieces.getPieceType(from);

        if (validMoveAdjacencyCheck(from, 8, 1, 2, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, 1, 2, 3, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, 2, 3, 4, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, 3, 4, 5, emptySpace) ) {
            return true;
        }
        if(validMoveAdjacencyCheck(from, 3, 4, 5, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, 4, 5, 6, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, 5, 6, 7, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, 6, 7, 8, emptySpace) ){
            return true;
        }
        if(validMoveAdjacencyCheck(from, 7, 8, 1, emptySpace)){
            return true;
        }

        //From the middle spot, you can move anywhere
        if (from == 9){
            return true;
        }
            return false;
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
    private boolean validMoveAdjacencyCheck(int from, int n1, int c, int n2, int emptySpace){
        String fromType = pieces.getPieceType(from);
        if ((from == c && isColor(n1, fromType) && isColor(n2, fromType))) {
            return false;
        }

        if(!validMoveEmptySpaceCheck(from, n1, c, n2, emptySpace)){
            return false;
        }
        return true;
    }

    private boolean validMoveEmptySpaceCheck(int from, int n1, int c, int n2, int emptySpace){
        if((from == c && (emptySpace == n1 || emptySpace == n2 || emptySpace == 9))){
            return true;
        }
        return false;
    }

    /**
     * Checks if piece is a color
     *
     * @param piece the id of the piece (1-9)
     * @param color can be black or white
     * @return returns true if the piece is the specified color.
     */
    public boolean isColor(int piece, String color){
        if(pieces.getPieceType(piece).contains(color)){
            return true;
        }
        return false;
    }

}
