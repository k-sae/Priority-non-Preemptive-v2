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
               i = this.addResult(this.deleteProcesses(number),i);
            }
            else {
                addEmptyResult(i);
                i++;
            }
        }

    }
    public  ArrayList<ProcessResult> endResults (){
        return this.processResults;
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
    private void  addEmptyResult  (int time){
        ProcessResult processResult = new ProcessResult();
        int  length = processResults.size();
        int check;
        if(length == 0) {
             check = 1;
        }
        else {
           check = processResults.get(length-1).getNumber();

        }
            if (check != 0) {
                processResult.setNumber(0);
                processResult.setStartTime(time);
                processResult.setEndTime(time + 1);
                processResult.setWaitingTime(0);
                processResult.setResponseTime(0);
                processResult.setTurnaroundTime(0);
                processResults.add(processResult);
            }

        else {
                processResult = processResults.get(processResults.size() - 1);
                processResult.setEndTime(processResult.getEndTime() + 1);
                processResults.set(processResults.size() - 1, processResult);

            }


    }
    private  int  findPriority (int time) {
        length = this.processes.size();
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
