package com.company;

/**
 * Created by khaled on 08/04/17.
 */
public class ProcessResult {
    private  int number;
    private  int startTime;
    private  int endTime;
    private  int waitingTime;
    private  int turnaroundTime;
    private  int  ResponseTime;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getResponseTime() {
        return ResponseTime;
    }

    public void setResponseTime(int responseTime) {
        ResponseTime = responseTime;
    }
}
