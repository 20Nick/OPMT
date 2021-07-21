package one.scarecrow.games.OPMT;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        Button playBtn = findViewById(R.id.playbtn);
        Button playBtn2 = findViewById(R.id.playbtn2);

        Button helpButton = findViewById(R.id.howPlayBtn);

        // Game activity intent
        myIntent = new Intent(this, GameActivity.class);

        helpButton.setOnClickListener(view -> {
            String url = "https://github.com/20Nick/OPMT/blob/main/README.md";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        playBtn.setOnClickListener(view -> {
            values.localMultiplayer = false;
            showComputerBox();
        });

        playBtn2.setOnClickListener(view -> {
            values.localMultiplayer = true;
            startActivity(myIntent);
        });

    }


    private void showComputerBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        builder.setCancelable(true);
        builder.setTitle(getString(R.string.go_first_query));
        builder.setPositiveButton(getString(R.string.computer),
                (dialog, which) -> {
                    values.isWhiteComputer = false;
                    startActivity(myIntent);
                    dialog.dismiss();
                });
        builder.setNegativeButton(getString(R.string.human),
                (dialog, which) -> {
                    values.isWhiteComputer = true;
                    //Switch to game activity
                    startActivity(myIntent);
                    dialog.dismiss();

                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}