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
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TriviaBuzzer {
    private int buzzers[] = new int[9];
    private String FILENAME = "BuzzerStats.sav";
    private Context context;

    public TriviaBuzzer(Context context){
        this.context = context;
        //loadFromFile();
    }

    public void incrementPlayer(int player, int base){
        buzzers[base + (player - 1)]++;

    }

    public int getPlayer(int player, int base){
        return buzzers[base + (player - 1)];
    }

    public ArrayList<String> getStats(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("\nTwo Player Mode\n\t\t\tPlayer One: "+ getPlayer(1, 0) + " Wins\n" +
                "\t\t\tPlayer Two: "+ getPlayer(2, 0) + " Wins");
        strings.add("\nThree Player Mode\n" +
                "\t\t\tPlayer One: "+ getPlayer(1, 2) + " Wins\n" +
                "\t\t\tPlayer Two: "+ getPlayer(2, 2) + " Wins\n" +
                "\t\t\tPlayer Three: "+ getPlayer(3, 2) + " Wins");
        strings.add("\nFour Player Mode\n" +
                "\t\t\tPlayer One: "+ getPlayer(1, 5) + " Wins\n" +
                "\t\t\tPlayer Two: "+ getPlayer(2, 5) + " Wins\n" +
                "\t\t\tPlayer Three: "+ getPlayer(3, 5) + " Wins\n" +
                "\t\t\tPlayer Four: " + getPlayer(4, 5) + " Wins");
        return strings;
    }

    public void clear(){
        for(int i = 0; i < buzzers.length; i++){
            buzzers[i] = 0;
        }
        saveInFile();
    }

    //Code modified with permission from Joshua Campbell
    //https://github.com/joshua2ua/lonelyTwitter
    //Date: Sep 28/2015
    public void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            buzzers = gson.fromJson(in, int[].class);
        } catch (FileNotFoundException e) {
            buzzers = new int[9];
        } catch (IOException e) {
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
            gson.toJson(buzzers, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
