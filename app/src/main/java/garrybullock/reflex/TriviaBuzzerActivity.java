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

public class TriviaBuzzerActivity extends AppCompatActivity {
    private int numPlayers;
    private TriviaBuzzerGame game = new TriviaBuzzerGame(TriviaBuzzerActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Based on a question from stack overflow
        //StackOverflow: http://stackoverflow.com/questions/7175881/android-start-intent-with-different-layout
        //User: DeeV
        //Oct 3 2015
        Bundle parameters = getIntent().getExtras();
        setContentView(parameters.getInt("layout"));
        //get the appropriate number of players from the intent
        numPlayers = parameters.getInt("numPlayers");
        switch (numPlayers){
            case 2:
                game.setBase(0);
                break;
            case 3:
                game.setBase(2);
                break;
            case 4:
                game.setBase(5);
                break;
        }
        buildDismissibleMessage("After clicking get started, the game will pop up a message with " +
                "who clicked first. Click next question to move on!", "Get Started");
        game.loadGame();
    }

    @Override
    public void onPause(){
        super.onPause();
        game.saveGame();
    }

    public void playerOneClick(View v){
        if(game.playerClicked(1)){
            buildDismissibleMessage("Player One clicked first!", "Next Question");
        }
    }

    public void playerTwoClick(View v){
        if(game.playerClicked(2)){
            buildDismissibleMessage("Player Two clicked first!", "Next Question");
        }
    }

    public void playerThreeClick(View v){
        if(game.playerClicked(1)){
            buildDismissibleMessage("Player Three clicked first!", "Next Question");
        }
    }

    public void playerFourClick(View v){
        if(game.playerClicked(1)){
            buildDismissibleMessage("Player Four clicked first!", "Next Question");
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
                game.resetGame();
                game.saveGame();
            }
        });
        builder.show();
    }
}
