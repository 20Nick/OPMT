package com.Scarecrow.games.OPMT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {


    // Init vars
    Button B1, B2, B3, B4, B5, B6, B7, B8, B9;

    // When pressed for the first time, the button will become activated and the second press of an another button will move
    int currentButtonActiveId;



    TextView currentActiveTextBox;

    // Grabs all values from the values class, changed from the main menu
    Boolean isWhiteTurn = values.isWhiteTurn;
    // Play on one screen with someone else!
    Boolean localMultiplayer = values.localMultiplayer;
    Boolean isWhiteComputer = values.isWhiteComputer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        //Change bottom nav bar to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }



          // How to switch to a different activity
//        Intent myIntent = new Intent(this, Game.class);
//        startActivity(myIntent);

        // Defining vars
        currentActiveTextBox = (TextView) findViewById(R.id.currentActiveTextbox);
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);
        B3 = (Button) findViewById(R.id.B3);
        B4 = (Button) findViewById(R.id.B4);
        B5 = (Button) findViewById(R.id.B5);
        B6 = (Button) findViewById(R.id.B6);
        B7 = (Button) findViewById(R.id.B7);
        B8 = (Button) findViewById(R.id.B8);
        B9 = (Button) findViewById(R.id.B9);



        //Making the textbox show the current turn
        currentActiveTextBox.setText("It is currently " + whosTurn(isWhiteTurn) + " turn.");
        // Updating each button background value
        buttonReset();


        // On click listeners for each button,
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B1);
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B2);
            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B3);
            }
        });

        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B4);
            }
        });

        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B5);
            }
        });

        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B6);
            }
        });

        B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B7);
            }
        });

        B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B8);
            }
        });

        B9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnClickMethod(B9);
            }
        });

        // if local multiplayer is off and computer is black, move computer
        if (!localMultiplayer && (!isWhiteComputer && !isWhiteTurn)){
            moveRandomPiece(false);
            //update all peieces
            buttonReset();
        }

    }

    /* TODO
     *  More themes?
     *  Add how to play
     *  Clean up code
     *  Make it clear what the AI is doing?
     */

    // Goes through each button and updates the button background and color
    private void buttonReset(){
        setBackgroundResourceValue(B1);
        setBackgroundResourceValue(B2);
        setBackgroundResourceValue(B3);
        setBackgroundResourceValue(B4);
        setBackgroundResourceValue(B5);
        setBackgroundResourceValue(B6);
        setBackgroundResourceValue(B7);
        setBackgroundResourceValue(B8);
        setBackgroundResourceValue(B9);
    }

    // This is the function that gets called when a button is pressed
    private void buttonOnClickMethod(Button B){

        // Maybe doing a one click? calling move button will always be the empty button.

        //If no current button is active then make this one active and check if it is the right turn
        if(currentButtonActiveId == 0 && ((B.getText().equals("W") && isWhiteTurn) || (B.getText().equals("B") && !isWhiteTurn))){
            currentButtonActiveId = B.getId(); // selecting the button
        } else if (currentButtonActiveId == B.getId()){  // if doubled tap on it, deactivate it.
            currentButtonActiveId = 0;
        }else if(currentButtonActiveId != 0) {
            moveButton(B); // if a button is already active, move button
            // B should always be the empty button
            // can make one click, do I want that?
        }
        // updates all button color background
        buttonReset();
    }

    /* Takes the button and assigns the right color to it
     *
     */
    private void setBackgroundResourceValue(Button B){
        if(B.getText().equals("B")){ // if the button is black piece
            // if the button is selected and able to move to the empty spot, make it look selected
            if(currentButtonActiveId == B.getId() && validMove(B, findEmptySpace())){
                B.setBackgroundResource(R.drawable.blackselected); // Setting xml file
                // if it is not a valid move and it is selected, unselect it
            } else if(currentButtonActiveId == B.getId()) {
                currentButtonActiveId = 0; // unselecting the button
            } else { // if it is a normal black button
                B.setBackgroundResource(R.drawable.blackbutton);
            }
        }else if (B.getText().equals("W")){
            if(currentButtonActiveId == B.getId() && validMove(B, findEmptySpace())){
                B.setBackgroundResource(R.drawable.whiteselected);
            }else if(currentButtonActiveId == B.getId()) {
                currentButtonActiveId = 0;
            } else {
                B.setBackgroundResource(R.drawable.whitebutton);
            }
        }else { // If it is none of the above, it is an empty button
            B.setBackgroundResource(R.drawable.emptybutton);
        }
    }


    // Can and should delete
    /* TODO
        Delete this method
     */
    private void dig(){
        currentActiveTextBox.setText("Current turn is : "+ whosTurn(isWhiteTurn) + " : " + currentButtonActiveId);
    }

    private boolean isWin(){ // Checks if there is a winner
        // Finds the empty space
        Button emptySpace = findEmptySpace();

        if(isWhiteTurn){
            // finds all white pieces
            Button[] allWhitePieces = findAllWhite();
            for(Button c : allWhitePieces ){ // For each loop of allWhitePieces
                if(c != null){ // if there is in fact a button
                    if (validMove(c, emptySpace)){ // Checks if the button c can move to the empty space
                        return false; // c can move to the empty space, means there is not a win
                    }
                }
            }
            return true; // Loop through the list and did not find a valid move
        }else {
            Button[] allBlackPieces = findAllBlack();
            for (Button c : allBlackPieces) {
                if (c != null){
                    if (validMove(c, emptySpace)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private Button[] findAllWhite(){
        // Makes a button array
        Button[] r = new Button[10];
        //Goes through each piece to find all white pieces
        if(B1.getText().equals("W")){
            r[1] = B1;
        }
        if(B2.getText().equals("W")){
            r[2] = B2;
        }
        if(B3.getText().equals("W")){
            r[3] = B3;
        }
        if(B4.getText().equals("W")){
            r[4] = B4;
        }
        if(B5.getText().equals("W")){
            r[5] = B5;
        }
        if(B6.getText().equals("W")){
            r[6] = B6;
        }
        if(B7.getText().equals("W")){
            r[7] = B7;
        }
        if(B8.getText().equals("W")){
            r[8] = B8;
        }
        if(B9.getText().equals("W")){
            r[9] = B9;
        }
        return r;
    }
    private Button[] findAllBlack(){
        // Makes a button array
        Button[] r = new Button[10];
        //Goes through each piece to find all black pieces
        if(B1.getText().equals("B")){
            r[1] = B1;
        }
        if(B2.getText().equals("B")){
            r[2] = B2;
        }
        if(B3.getText().equals("B")){
            r[3] = B3;
        }
        if(B4.getText().equals("B")){
            r[4] = B4;
        }
        if(B5.getText().equals("B")){
            r[5] = B5;
        }
        if(B6.getText().equals("B")){
            r[6] = B6;
        }
        if(B7.getText().equals("B")){
            r[7] = B7;
        }
        if(B8.getText().equals("B")){
            r[8] = B8;
        }
        if(B9.getText().equals("B")){
            r[9] = B9;
        }
        return r;
    }

    private Button findEmptySpace(){ // Goes through each piece to find the empty space
        if(B1.getText().equals("E")){
            return B1;
        }
        if(B2.getText().equals("E")){
            return B2;
        }
        if(B3.getText().equals("E")){
            return B3;
        }
        if(B4.getText().equals("E")){
            return B4;
        }
        if(B5.getText().equals("E")){
            return B5;
        }
        if(B6.getText().equals("E")){
            return B6;
        }
        if(B7.getText().equals("E")){
            return B7;
        }
        if(B8.getText().equals("E")){
            return B8;
        }
        if(B9.getText().equals("E")){
            return B9;
        }
        //Should never get here because going through each space....
        return null;
    }

    // Goes through each piece to check if the move is valid
    private boolean validMove(Button from, Button to){

        //Checks if adjacent pieces are yours
        if(from.getText().equals("W") && (to.getId() == B9.getId())){
            Button[] allWhitePieces = findAllWhite();
            for (int i = 0; i < allWhitePieces.length; i++){
                if(allWhitePieces[i] != null){
                    if (allWhitePieces[i].getId() == from.getId()){
                        if((allWhitePieces[i-1] != null && allWhitePieces[i+1] != null) || (from.getId() == B1.getId() && (allWhitePieces[8] != null && allWhitePieces[2] != null)) || (from.getId() == B8.getId() && (allWhitePieces[1] != null && allWhitePieces[7] != null))){
                            return false;
                        }
                    }
                }
            }
        }

        if(from.getText().equals("B") && (to.getId() == B9.getId())){
            Button[] allBlackPieces = findAllBlack();
            for (int i = 0; i < allBlackPieces.length; i++){
                if(allBlackPieces[i] != null){
                    if (allBlackPieces[i].getId() == from.getId()){
                        if(allBlackPieces[i-1] != null && allBlackPieces[i+1] != null || (from.getId() == B1.getId() && (allBlackPieces[8] != null && allBlackPieces[2] != null)) || (from.getId() == B8.getId() && (allBlackPieces[1] != null && allBlackPieces[7] != null))){
                            return false;
                        }
                    }
                }
            }
        }

        // If B9, can move anywhere.
        if(from.getId() == B9.getId() || to.getId() == B9.getId()){
            return true;
        }

        // From B1, checks the around valid places
        if((from.getId() == B1.getId() && ( (to.getId() == B2.getId()) || (to.getId() == B9.getId() || (to.getId() == B8.getId()))))
                || to.getId() == B1.getId() && ( (from.getId() == B2.getId()) || (from.getId() == B9.getId() || (from.getId() == B8.getId())))){
            return true;
        }

        // From B2, checks the around valid places
        if((from.getId() == B2.getId() && ( (to.getId() == B1.getId()) || (to.getId() == B9.getId() || (to.getId() == B3.getId()))))
                || to.getId() == B2.getId() && ( (from.getId() == B1.getId()) || (from.getId() == B9.getId() || (from.getId() == B3.getId())))){
            return true;
        }

        // From B3, checks the around valid places
        if((from.getId() == B3.getId() && ( (to.getId() == B2.getId()) || (to.getId() == B9.getId() || (to.getId() == B4.getId()))))
                || to.getId() == B3.getId() && ( (from.getId() == B2.getId()) || (from.getId() == B9.getId() || (from.getId() == B4.getId())))){
            return true;
        }

        // From B4, checks the around valid places
        if((from.getId() == B4.getId() && ( (to.getId() == B3.getId()) || (to.getId() == B9.getId() || (to.getId() == B5.getId()))))
                || to.getId() == B4.getId() && ( (from.getId() == B3.getId()) || (from.getId() == B9.getId() || (from.getId() == B5.getId())))){
            return true;
        }

        // From B5, checks the around valid places
        if((from.getId() == B5.getId() && ( (to.getId() == B4.getId()) || (to.getId() == B9.getId() || (to.getId() == B6.getId()))))
                || to.getId() == B5.getId() && ( (from.getId() == B4.getId()) || (from.getId() == B9.getId() || (from.getId() == B6.getId())))){
            return true;
        }

        // From B6, checks the around valid places
        if((from.getId() == B6.getId() && ( (to.getId() == B5.getId()) || (to.getId() == B9.getId() || (to.getId() == B7.getId()))))
                || to.getId() == B6.getId() && ( (from.getId() == B5.getId()) || (from.getId() == B9.getId() || (from.getId() == B7.getId())))){
            return true;
        }

        // From B7, checks the around valid places
        if((from.getId() == B7.getId() && ( (to.getId() == B6.getId()) || (to.getId() == B9.getId() || (to.getId() == B8.getId()))))
                || to.getId() == B7.getId() && ( (from.getId() == B6.getId()) || (from.getId() == B9.getId() || (from.getId() == B8.getId())))){
            return true;
        }

        // From B8, checks the around valid places
        if((from.getId() == B8.getId() && ( (to.getId() == B7.getId()) || (to.getId() == B9.getId() || (to.getId() == B1.getId()))))
                || to.getId() == B8.getId() && ( (from.getId() == B7.getId()) || (from.getId() == B9.getId() || (from.getId() == B1.getId())))){
            return true;
        }

        return false;
    }

    // Moves the button 'currentButtonActive' to 'moveToButton'
    private void moveButton(Button moveToButton){ // moveToButton is always the empty button

        currentActiveTextBox.setText("It is currently " + whosTurn(isWhiteTurn) + "turn.");

        // If the ID is 0, do nothing. Incase this method is called without anything selected
        if(currentButtonActiveId == 0){
            return;
        }

        // init currentButtonActive by using the id stored in the global var currentButtonActiveId
        Button currentButtonActive = (Button) findViewById(currentButtonActiveId);
        // Making sure moveToButton is the empty space and that the active button is  not the empty space
        if(moveToButton.getText().equals("E") && !currentButtonActive.getText().equals("E")){
            //  Check if the move is valid                      Check if the whos turn it is, and making sure they move the right piece
            if (validMove(currentButtonActive, moveToButton) && ((isWhiteTurn && currentButtonActive.getText().equals("W")) || (!isWhiteTurn && currentButtonActive.getText().equals("B")))){
                // Puts ethier B or W in playerColor, depending on whos turn it is and the current button
                String playerColor = (String) currentButtonActive.getText();
                // sets the Empty button to the currentButtonAtive text
                moveToButton.setText(playerColor);

                // Sets the button moving to empty
                currentButtonActive.setText("E");

                //Ends turn
                isWhiteTurn = !isWhiteTurn;
                currentButtonActiveId = 0;
                currentActiveTextBox.setText("It is currently " + whosTurn(isWhiteTurn) + "turn.");

                // Checks if there is any winner, The winner will be (!isWhiteTurn)
                if(isWin()){
                    // Creates a pop up message, saying there has been a winner and if you would like to play again
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(whosTurn(!isWhiteTurn) + " has won this round!")
                            .setCancelable(false)
                            .setPositiveButton("Restart?", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Restarts the activity
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }else { // If the game is still active ( no win )
                    //If a computer is playing and it is the computers turn.
                    if(!localMultiplayer && (isWhiteComputer && isWhiteTurn)){
                        moveRandomPiece(true);
                    }else if(!localMultiplayer && (!isWhiteComputer && !isWhiteTurn)){
                        moveRandomPiece(false);
                    }
                }

            }

        }
    }


    /* This is the 'AI'
     *   It finds all pieces it owns and then find all the valid moves that it can take, then rolls a random number generator, and makes a move
     */
    private void moveRandomPiece(boolean moveWhite){

        // returns empty space
        Button emptySpace = findEmptySpace();

        // init button array
        Button[] validButtonMoves = new Button[10];
        if(moveWhite){ // if computer is white
            // Get all white pieces and place them in a button array
            Button[] allWhitePieces = findAllWhite();
            // Goes through each piece
            for (int i = 0; i < allWhitePieces.length; i++){
                if(allWhitePieces[i] != null){
                    if(validMove(allWhitePieces[i], emptySpace)){ // if the piece is valid to move to the empty space, add to valid moves
                        validButtonMoves[i] = allWhitePieces[i];
                    }
                }
            }
        }else { // if computer is black

            // Add all black pieces into array
            Button[] allBlackPieces = findAllBlack();
            // Goes through each piece
            for (int i = 0; i < allBlackPieces.length; i++){
                if(allBlackPieces[i] != null){
                    if(validMove(allBlackPieces[i], emptySpace)){ // if the piece is valid to move to the empty space, add to valid moves
                        validButtonMoves[i] = allBlackPieces[i];
                    }
                }
            }
        }
        Random g = new Random(); // Setting up random number gen
        int r = g.nextInt(10);
        // counter
        int c = 0;
        while(validButtonMoves[r] == null){ //picking a random move from validButtons
            r = g.nextInt(10);
            c++;
            //       in tests, c never got above 30
            if (c >= 1000){     //If cant find a move, forfeit the match.
                break;
            }
        }
        if(c >= 1000 && validButtonMoves[r] == null){ // Basically to stop if the while loop is trying to go forever
            Log.d("AI", "Ai has forfeit the game at + " + c);
            forfeit();
        }else {
            Log.d("AI", "It took the ai " + c + " times to find a suitable number.");
            currentButtonActiveId = validButtonMoves[r].getId();
            moveButton(emptySpace);
        }

    }

    private void forfeit() { // If there is a case where the while does not stop, move here and ask for a new match
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ai has forfeited the mach!! Player has won this round!")
                .setCancelable(false)
                .setPositiveButton("Restart?", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Restarts the activity
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private String whosTurn(boolean isWhiteTurn){ // Returns whos turn it is as a string
        String currentTurn;
        if(isWhiteTurn){
            currentTurn = "White";

        }else{
            currentTurn = "Black";
        }
        return currentTurn;
    }

}