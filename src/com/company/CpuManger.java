package com.company;

import java.util.ArrayList;

/**
 * Created by khaled on 08/04/17.
 */
public  class  CpuManger {
    private  ArrayList<Process> processes;
    private  ArrayList <ProcessResult> processResults;
    int length ;
    public CpuManger(ArrayList<Process> processes) {
         this.processes=processes;
         processResults = new ArrayList<>();
        int i = 0;
        int number;
        while (!processes.isEmpty()){
            number = findPriority(i);
            if(number !=0){
               i = addResult(deleteProcesses(number),i);
            }
            else {
                i++;
            }
        }

    }
    public  ArrayList<ProcessResult> endResults (){
        return processResults;
    }
    private Process deleteProcesses (int number){
        Process process=new Process();
        length = processes.size();
        int i=0;
        do{
            if(processes.get(i).getNumber() == number){
                process = processes.get(i);
                i=length;
            }
            else {
                i++;
            }
        }while (i< length);
        processes.remove(process);
        return  process;
    }
    private int  addResult  (Process process, int time){
        ProcessResult processResult = new ProcessResult();
        int start ;
        int end;
        processResult.setNumber(process.getNumber());
        start = time;
        processResult.setStartTime(start);
        end=start+process.getBurstTime();
        processResult.setEndTime(end);
        int ArrivalTime =process.getArrivalTime();
        processResult.setWaitingTime(start-ArrivalTime);
        processResult.setResponseTime(start-ArrivalTime);
        processResult.setTurnaroundTime(end-ArrivalTime);
        processResults.add(processResult);
        return end;
    }
    private  int  findPriority (int time) {
        length = processes.size();
        Process process = new Process();
        int miniPriority =0 ;
        int number =0 ;
        for (int i = 0; i < length; i++) {
            if (processes.get(i).getArrivalTime() <= time) {
                int processPriority =processes.get(i).getPriority();
                if (miniPriority == 0||miniPriority > processPriority ){
                    number=processes.get(i).getNumber();
                   miniPriority = processPriority;
                }

            }
        }

        return  number;
    }

}
