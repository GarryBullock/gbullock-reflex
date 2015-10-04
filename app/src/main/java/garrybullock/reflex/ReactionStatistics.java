package garrybullock.reflex;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Garry on 2015-09-27.
 */
public class ReactionStatistics {

    protected ArrayList<Integer> statList;
    public ReactionStatistics() {
        this.statList = new ArrayList<>();
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

    public ArrayList<String> getStatStrings(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add(minStats());
        strings.add(maxStats());
        strings.add(avgStats());
        strings.add(medianStats());
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
        return ("Average Time \n" +
                "\t\t\tLast 10: "+ getAvg(getLastX(10))+ " ms\n" +
                "\t\t\tLast 100: "+ getAvg(getLastX(100)) + " ms\n" +
                "\t\t\tAll Time: " + getAvg(statList) + " ms");
    }

    private String medianStats(){
        return ("Median Time \n" +
                "\t\t\tLast 10: "+ getMedian(getLastX(10)) + " ms\n" +
                "\t\t\tLast 100: "+ getMedian(getLastX(100)) + " ms\n" +
                "\t\t\tAll Time: "+ getMedian(statList)) + " ms";
    }

    private Integer getAvg(ArrayList<Integer> stats){
        //Code designed based on StackOverflow answer by user Jeshurun
        //http://stackoverflow.com/questions/10791568/calculating-average-of-an-array-list
        //Oct 3 2015
        if (stats.isEmpty()){
            return 0;
        }
        Integer sum = 0;
        for (Integer i: stats) {
            sum += i;
        }
        return sum / stats.size();
    }

    private Integer getMedian(ArrayList<Integer> stats){
        Collections.sort(stats);
        //if the array is even, we have to return a value in between the most middle two values
        if((stats.size() % 2) == 0){
            return ((stats.get((stats.size()/2)-1) + stats.get(stats.size()/2))/2);
        }
        else return (stats.get(stats.size()/2));
    }
}