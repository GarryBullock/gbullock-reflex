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
/*
    TriviaBuzzerGame is responsible for controlling the trivia buzzer. It recieves player clicks
    from TriviaBuzzerActivity, and then checks if they are a valid press. If they are it increments
    that players number of wins via TriviaBuzzer.
 */
public class TriviaBuzzerGame {
    private boolean validPress;
    private int arrayBase;
    private TriviaBuzzer triviaBuzzer;

    public TriviaBuzzerGame(Context context){
        this.triviaBuzzer = new TriviaBuzzer(context);
        validPress = true;
    }

    public boolean playerClicked(int player){
        if(validPress){
            validPress = false;
            triviaBuzzer.incrementPlayer(player, arrayBase);
            return true;
        }
        else return false;
    }

    public void setBase(int arrayBase){
        this.arrayBase = arrayBase;
    }

    public void saveGame(){
        triviaBuzzer.saveInFile();
    }

    public void loadGame(){
        triviaBuzzer.loadFromFile();
    }

    public void resetGame(){
        validPress = true;
    }

}
