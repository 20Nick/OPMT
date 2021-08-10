package one.scarecrow.games.OPMT;

import static android.graphics.Color.*;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class settingsActivity extends AppCompatActivity {

    Button saveBtn, restoreDefault;
    EditText boardColor, player1Color, player2Color, backgroundColor, textColor;

    private UserSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = (UserSettings) getApplication();


        saveBtn = findViewById(R.id.saveSettingsBtn);
        restoreDefault = findViewById(R.id.restoreDefaults);
        boardColor = findViewById(R.id.boardColorTxt);
        player1Color = findViewById(R.id.player1Color);
        player2Color = findViewById(R.id.player2Color);
        backgroundColor = findViewById(R.id.backgroundColor);
        textColor = findViewById(R.id.textColor);

        loadSharedPreferences();


        saveBtn.setOnClickListener(view -> save());
        restoreDefault.setOnClickListener(view -> restoreDefault());
    }

    private void restoreDefault(){
        settings.restoreDefault();
        updateView();
    }

    private void loadSharedPreferences() {
        backgroundColor.setText(settings.getPref("backgroundColor"));
        textColor.setText(settings.getPref("textColor"));
        boardColor.setText(settings.getPref("boardColor"));
        player1Color.setText(settings.getPref("player1Color"));
        player2Color.setText(settings.getPref("player2Color"));
        updateView();
    }

    public void save(){
        String backgroundColorTxt = String.valueOf(backgroundColor.getText());
        String textColorTxt = String.valueOf(textColor.getText());
        String boardColorTxt = String.valueOf(boardColor.getText());
        String player1ColorTxt = String.valueOf(player1Color.getText());
        String player2ColorTxt = String.valueOf(player2Color.getText());

        if(checkIfColor(backgroundColorTxt) && checkIfColor(textColorTxt) && checkIfColor(boardColorTxt) && checkIfColor(player1ColorTxt) && checkIfColor(player2ColorTxt)){
            settings.setPref("backgroundColor", backgroundColorTxt);
            settings.setPref("textColor", textColorTxt);
            settings.setPref("boardColor", boardColorTxt);
            settings.setPref("player1Color", player1ColorTxt);
            settings.setPref("player2Color", player2ColorTxt);
        }else {
            Toast t = Toast.makeText(this, "Invalid color! ", Toast.LENGTH_LONG);
            t.show();
        }
        updateView();
    }

    /**
     * Checks if the input string is a hex color
     * @param color input string that is being checked
     * @return true if the string is a hex color
     */
    public boolean checkIfColor(String color){
        try {
            parseColor(color);
        }catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }

    private void updateView() {
        //Background Color
        this.getWindow().getDecorView().setBackgroundColor(Color.parseColor(settings.getPref("backgroundColor")));
        //Text color
        TextView d = findViewById(R.id.textView);
        d.setTextColor(Color.parseColor(settings.getPref("textColor")));

        d = findViewById(R.id.textView2);
        d.setTextColor(Color.parseColor(settings.getPref("textColor")));

        d = findViewById(R.id.textView3);
        d.setTextColor(Color.parseColor(settings.getPref("textColor")));

        d = findViewById(R.id.textView4);
        d.setTextColor(Color.parseColor(settings.getPref("textColor")));

        d = findViewById(R.id.textView5);
        d.setTextColor(Color.parseColor(settings.getPref("textColor")));

        d = findViewById(R.id.textView6);
        d.setTextColor(Color.parseColor(settings.getPref("textColor")));
    }
}