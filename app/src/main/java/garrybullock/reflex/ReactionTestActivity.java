package garrybullock.reflex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class ReactionTestActivity extends AppCompatActivity {

    private boolean validPress = false;
    private long userTapTime;
    private final Handler h = new Handler();
    private boolean firstLoop = true;

    final static int minTime = 10;
    final static int maxTime = 2000;

    private Button button;
    private TextView text;
    public  Statistics stat;

    Runnable reactionGame = new Runnable() {
        @Override
        public void run() {
            userTapTime = System.currentTimeMillis();
            validPress = true;
            text.setText("Tap!");
            button.setBackgroundColor(0xff12c20a);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_test);
        stat = new Statistics();
        button = (Button) findViewById(R.id.reactionButton);
        text = (TextView) findViewById(R.id.reactionTestText);

        buildDismissibleMessage("Wait until the button goes from red to green, and the text tells" +
                " you to tap, then tap as quickly as possible!");
        initializeListeners();
    }

    @Override
    public void onPause(){
        super.onPause();
        //if we leave the app, stop the timer.
        h.removeCallbacks(reactionGame);
    }

    //currently on resume will play the first time through... must stop this
    @Override
    public void onResume(){
        super.onResume();
        //checking if it is the first time through the loop ensures this post wont be done while
        //the initial message is being played.
        if(validPress) {
            text.setText("Wait for it...");
            button.setBackgroundColor(0xfff84525);
        }
        validPress = false;
        if(!firstLoop) {
            h.postDelayed(reactionGame, randomTime());
        }
        firstLoop = false;
    }

    private void initializeListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validPress){
                    long reactionTime =  (System.currentTimeMillis() - userTapTime);
                    validPress = false;
                    buildDismissibleMessage("Reaction Time: " + String.valueOf(reactionTime) + "ms.");
                    stat.addStat((int) reactionTime);

                }
                else{
                    //This was an invalid press. Stop the handler, display message, and restart the handler
                    h.removeCallbacks(reactionGame);
                    buildDismissibleMessage("Wait until you are told to tap!");
                    validPress = false;
                }
            }
        };
        final Button button = (Button) findViewById(R.id.reactionButton);
        button.setOnClickListener(listener);
    }

    private void buildDismissibleMessage(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(ReactionTestActivity.this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                h.postDelayed(reactionGame, randomTime());
                text.setText("Wait for it...");
                button.setBackgroundColor(0xfff84525);
            }
        });
        builder.show();
    }

    //need to cite? stackoverflow: http://stackoverflow.com/questions/6029495/how-can-i-generate-random-number-in-specific-range-in-android
    //user: Mr.Polywhirl
    public long randomTime(){
        Random random = new Random();
        return random.nextInt(maxTime - minTime) + minTime;
    }

        /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_test, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
        */
}
