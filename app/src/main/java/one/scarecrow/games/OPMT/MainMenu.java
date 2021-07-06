package one.scarecrow.games.OPMT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Intent myIntent;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        //  Night mode looks nice right now.... maybe going to add more themes
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        //Change bottom nav bar to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        Button playbtn = (Button) findViewById(R.id.playbtn);
        Button playbtn2 = (Button) findViewById(R.id.playbtn2);

        Button helpButton = (Button) findViewById(R.id.howPlayBtn);

        // Game activity intent
        myIntent = new Intent(this, GameActivity.class);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://github.com/20Nick/OPMT/blob/main/README.md";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.localMultiplayer = false;
                showComputerBox();
            }
        });

        playbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.localMultiplayer = true;
                startActivity(myIntent);
            }
        });

    }


    private void showComputerBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        builder.setCancelable(true);
        builder.setTitle("Who will go first");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Computer",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        values.isWhiteComputer = false;
                        startActivity(myIntent);
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("Human",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        values.isWhiteComputer = true;
                        //Switch to game activity
                        startActivity(myIntent);
                        dialog.dismiss();

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}