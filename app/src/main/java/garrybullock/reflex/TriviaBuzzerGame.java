package garrybullock.reflex;

import android.content.Context;

/**
 * Created by Garry on 2015-10-04.
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
