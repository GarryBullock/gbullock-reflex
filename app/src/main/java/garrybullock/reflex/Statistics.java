package garrybullock.reflex;

import java.util.ArrayList;

/**
 * Created by Garry on 2015-09-27.
 */
public class Statistics {

    private static final Statistics stats = new Statistics();
    protected ArrayList<Integer> statList;

    public Statistics() {
        this.statList = new ArrayList<>();
    }

    public ArrayList<Integer> getStatList() {
        return statList;
    }

    public void addStat(Integer integer){
        statList.add(integer);
    }

    private ArrayList<Integer> getLastX(Integer toGet){
        if(statList.size() <= 0){
            return null;
        }
        ArrayList<Integer> lastX = new ArrayList<>();
        for (int i = toGet; i > 0 ; i--) {
            lastX.add(statList.get((statList.size()-1)-i));
        }
        return lastX;
    }

    public static Statistics getInstance(){
        return stats;
    }

}
