package one.scarecrow.games.OPMT;

import android.app.Application;
import android.content.SharedPreferences;

public class UserSettings extends Application {

    public static final String PREFS_NAME = "MyPrefsFile";



    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public void onCreate() {
        super.onCreate();
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        if(getPref("boardColor").isEmpty()){
            restoreDefault();
        }
    }
    public void restoreDefault(){
        setPref("boardColor","#3A3A3A");
        setPref("textColor","#ffffff");
        setPref("player1Color", "#000000");
        setPref("player2Color", "#ffffff");
        setPref("backgroundColor", "#444444");

    }

    /**
     * Set a preference
     * @param pref the preference name
     * @param string the text to go in the preference
     */
    public void setPref(String pref, String string){
        editor.putString(pref, string);
        editor.commit();
    }

    /**
     * Gets the text of a preference
     * @param pref the preference name
     * @return the text that goes to the preference
     */
    public String getPref(String pref){
        return settings.getString(pref, "");
    }


}
