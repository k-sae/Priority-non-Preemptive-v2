package com.company;

import java.util.ArrayList;

/**
 * Created by khaled on 08/04/17.
 */
public class Results {
 private ArrayList<ProcessResult> processResults;
 private  float averageWaiting;
 private  float averageTurnaround;
 private  float averageResponse;

    public Results(ArrayList<Process> processes) {
        CpuManger cpuManger = new CpuManger(processes);
        processResults =cpuManger.endResults();
        int l= processResults.size();
        int  lenghtActive =0;
        for (int i=0;i<l;i++){
            if( processResults.get(i).getNumber() !=0) {
                averageWaiting += processResults.get(i).getWaitingTime();
                averageTurnaround += processResults.get(i).getTurnaroundTime();
                lenghtActive++;
            }
        }
        averageWaiting = averageWaiting / lenghtActive;
        averageTurnaround =averageTurnaround /lenghtActive ;
        averageResponse = averageWaiting;
    }

    public ArrayList<ProcessResult> getResults() {
        return processResults;
    }

    public float getAverageWaiting() {
        return averageWaiting;
    }

    public float getAverageTurnaround() {
        return averageTurnaround;
    }
    public float getAverageResponse() {
        return averageResponse;
    }

}
