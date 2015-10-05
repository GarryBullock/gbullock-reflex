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

import java.util.ArrayList;
import java.util.Collections;

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

    public void clear(){
        statList.clear();
    }

    private String minStats(){
        if(statList.isEmpty()){
            return ("\nMinimum Time \n" +
                    "\t\t\tLast 10:  "+ 0 + " ms\n" +
                    "\t\t\tLast 100: "+ 0 + " ms\n" +
                    "\t\t\tAll Time: "+ 0+ " ms");
        }
        return ("\nMinimum Time \n" +
                "\t\t\tLast 10:  "+ Collections.min(getLastX(10)) + " ms\n" +
                "\t\t\tLast 100: "+ Collections.min(getLastX(100)) + " ms\n" +
                "\t\t\tAll Time: "+ Collections.min(statList)+ " ms");
    }

    private String maxStats(){
        if(statList.isEmpty()){
            return ("\nMaximum Time \n" +
                    "\t\t\tLast 10:  "+ 0 + " ms\n" +
                    "\t\t\tLast 100: "+ 0 + " ms\n" +
                    "\t\t\tAll Time: "+ 0+ " ms");
        }
        return ("\nMaximum Time \n" +
                "\t\t\tLast 10: "+ Collections.max(getLastX(10)) + " ms\n" +
                "\t\t\tLast 100: "+ Collections.max(getLastX(100)) + " ms\n" +
                "\t\t\tAll Time: "+ Collections.max(statList)+ " ms");
    }

    private String avgStats(){
        return ("\nAverage Time \n" +
                "\t\t\tLast 10: "+ getAvg(getLastX(10))+ " ms\n" +
                "\t\t\tLast 100: "+ getAvg(getLastX(100)) + " ms\n" +
                "\t\t\tAll Time: " + getAvg(statList) + " ms");
    }

    private String medianStats(){
        return ("\nMedian Time \n" +
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
        if(stats.isEmpty()){
            return 0;
        }
        if((stats.size() % 2) == 0){
            return ((stats.get((stats.size()/2)-1) + stats.get(stats.size()/2))/2);
        }
        else return (stats.get(stats.size()/2));
    }
}
