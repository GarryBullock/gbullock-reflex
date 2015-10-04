package garrybullock.reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TriviaBuzzerActivity extends AppCompatActivity {
    private int numPlayers;
    private int arrayBase;
    private TriviaBuzzer buzzer = new TriviaBuzzer(TriviaBuzzerActivity.this);
    private boolean validPress = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Based on from stack overflow
        //StackOverflow: http://stackoverflow.com/questions/7175881/android-start-intent-with-different-layout
        //User: DeeV
        //Oct 3 2015
        Bundle parameters = getIntent().getExtras();
        setContentView(parameters.getInt("layout"));
        //get the appropriate number of players from the intent
        numPlayers = parameters.getInt("numPlayers");
        switch (numPlayers){
            case 2:
                arrayBase = 0;
                break;
            case 3:
                arrayBase = 2;
                break;
            case 4:
                arrayBase = 5;
                break;
        }
        buildDismissibleMessage("After clicking get started, the game will pop up a message with " +
                "who clicked first. Click next question to move on!", "Get Started");
        buzzer.loadFromFile();
    }

    @Override
    public void onPause(){
        super.onPause();
        buzzer.saveInFile();
    }

    public void playerOneClick(View v){
        if(validPress) {
            buzzer.incrementPlayer(1, arrayBase);
            buildDismissibleMessage("Player one clicked first!", "Next Question");
            validPress = false;
        }

    }

    public void playerTwoClick(View v){
        if(validPress) {
            buzzer.incrementPlayer(2, arrayBase);
            buildDismissibleMessage("Player two clicked first!", "Next Question");
            validPress = false;
        }
    }

    public void playerThreeClick(View v){
        if(validPress) {
            buzzer.incrementPlayer(3, arrayBase);
            buildDismissibleMessage("Player three clicked first!", "Next Question");
            validPress = false;
        }
    }

    public void playerFourClick(View v){
        if(validPress) {
            buzzer.incrementPlayer(4, arrayBase);
            buildDismissibleMessage("Player four clicked first!", "Next Question");
            validPress = false;
        }
    }

    private void buildDismissibleMessage(String message, String dismiss){
        AlertDialog.Builder builder = new AlertDialog.Builder(TriviaBuzzerActivity.this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(dismiss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                validPress = true;
                buzzer.saveInFile();
            }
        });
        builder.show();
    }
}
