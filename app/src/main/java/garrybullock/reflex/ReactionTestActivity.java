/*

        Copyright 2015 Garry Bullock

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.

*/
package garrybullock.reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
    ReactionActivity is the UI that presents ReactionGame to the player. It displays necessary
    messages, and relays button presses to ReactionGame for interpretation.
 */
public class ReactionTestActivity extends AppCompatActivity {
    private Button button;
    private TextView text;
    private ReactionGame game;
    private Boolean firstLoop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_test);
        button = (Button) findViewById(R.id.reactionButton);
        text = (TextView) findViewById(R.id.reactionTestText);
        game = new ReactionGame(button, text, ReactionTestActivity.this);
        buildDismissibleMessage("Wait until the button goes from red to green, and the text tells" +
                " you to tap, then tap as quickly as possible!");
        initializeListeners();
    }

    @Override
    public void onPause(){
        super.onPause();
        //if we leave the app, stop the timer.
        game.pauseGame();
        game.close();
    }

    @Override
    public void onResume(){
        super.onResume();
        //checking if it is the first time through the loop ensures this post wont be done while
        //the initial message is being played.
        if(game.isValidPress()) {
            text.setText("Wait for it...");
            button.setBackgroundColor(0xfff84525);
        }
        if(!firstLoop) {
            game.resumeGame();
        }
        else{
            firstLoop = false;
        }
    }

    private void initializeListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game.isValidPress()){
                    game.playGame();
                    buildDismissibleMessage("Reaction Time: " + game.getReactionTime() + "ms.");

                }
                else{
                    //This was an invalid press. Stop the handler, display message, and restart the handler
                    game.pauseGame();
                    buildDismissibleMessage("Wait until you are told to tap!");
                }
            }
        };
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
                game.startGame();
            }
        });
        builder.show();
    }

}
