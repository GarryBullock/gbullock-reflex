package garrybullock.reflex;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Garry on 2015-09-27.
 */
public class Statistics {

    private static Statistics stats = new Statistics();
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

    public ArrayList<Integer> getLastX(Integer toGet){
        if(statList.size() < toGet){
            toGet = statList.size();
        }

        ArrayList<Integer> lastX = new ArrayList<>();
        for (int i = toGet; i > 0 ; i--) {
            lastX.add(statList.get((statList.size())-i));
        }
        return lastX;
    }

    public static Statistics getInstance(){
        return stats;
    }

    public ArrayList<String> getStatStrings(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add(minStats());
        strings.add(maxStats());
        //strings.add(avgStats());
        //strings.add(medianStats());
        return strings;
    }

    private String minStats(){
        return ("Minimum Time \n" +
                "\t\t\tLast 10:  "+ Collections.min(getLastX(10)) + " ms\n" +
                "\t\t\tLast 100: "+ Collections.min(getLastX(100)) + " ms\n" +
                "\t\t\tAll Time: "+ Collections.min(statList)+ " ms");
    }

    private String maxStats(){
        return ("Maximum Time \n" +
                "\t\t\tLast 10: "+ Collections.max(getLastX(10)) + " ms\n" +
                "\t\t\tLast 100: "+ Collections.max(getLastX(100)) + " ms\n" +
                "\t\t\tAll Time: "+ Collections.max(statList)+ " ms");
    }

    private String avgStats(){
        int avg10;
        return ("Average Time \n" +
                "\t Last 10: "+ Collections.max(getLastX(10)) + "\n" +
                "\tLast 100: "+ Collections.max(getLastX(100)) + "\n" +
                "\tAll Time: "+ Collections.max(statList));
    }
}
