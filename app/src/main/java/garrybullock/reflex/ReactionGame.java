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


import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class ReactionGame {
    public boolean validPress;
    private long userTapTime;
    private final Handler h = new Handler();

    private Button button;
    private TextView text;
    private ReactionStatistics stats = new ReactionStatistics();
    private Integer reactionTime = 0;
    private Context context;
    private String FILENAME = "ReactionStats.sav";

    //Define a runnable that the handler will use to run the reaction game
    Runnable reactionGame = new Runnable() {
        @Override
        public void run() {
            userTapTime = System.currentTimeMillis();
            validPress = true;
            button.setBackgroundColor(0xff00CD00);
            text.setText("Tap!");
        }
    };

    public ReactionGame(View button, View text, Context context){
        validPress = false;
        this.button = (Button) button;
        this.text = (TextView) text;
        this.context = context;
        loadFromFile();
    }

    public ReactionGame(Context context){
        this.context = context;
    }

    public void playGame(){
        reactionTime = (int) (System.currentTimeMillis() - userTapTime);
        validPress = false;
        stats.addStat(reactionTime);
    }

    public boolean isValidPress(){
        return validPress;
    }

    //start a game
    public void startGame(){
        h.postDelayed(reactionGame, randomTime());
        text.setText("Wait for it...");
        button.setBackgroundColor(0xfff84525);
    }

    //pause the running of the game
    public void pauseGame(){
        h.removeCallbacks(reactionGame);
    }

    public int getReactionTime(){
        return reactionTime;
    }

    public void close(){
        saveInFile();
    }

    public void resumeGame() {
        validPress = false;
        loadFromFile();
        startGame();
    }

    public ReactionStatistics getStats(){
        loadFromFile();
        return stats;
    }

    public void clear(){
        stats.clear();
        close();
    }

    //stackoverflow: http://stackoverflow.com/questions/6029495/how-can-i-generate-random-number-in-specific-range-in-android
    //user: Mr.Polywhirl
    //Date: Sep 27/2015
    public long randomTime(){
        Random random = new Random();
        return random.nextInt(2000 - 10) + 10;
    }

    //Code modified with permission from Joshua Campbell
    //https://github.com/joshua2ua/lonelyTwitter
    //Date: Sep 28/2015
    private void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            stats = gson.fromJson(in, ReactionStatistics.class);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            stats = new ReactionStatistics();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    //Code modified with permission from Joshua Campbell
    //https://github.com/joshua2ua/lonelyTwitter
    //Date: Sep 28/2015
    public void saveInFile() {
        try {
            //MODE_APPEND appends to end of file, 0 is write mode
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(stats, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
