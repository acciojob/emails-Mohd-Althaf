package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();
        // The inboxCapacity is equal to the maximum value an integer can store.

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int maxmeets = 1;
        if(calendar.size()==0) return 0;
        Collections.sort(calendar, Comparator.comparing(Meeting::getStartTime));
        int prev = 0;
        for(int i=1;i<calendar.size();i++){
            if(calendar.get(i).getStartTime().compareTo(calendar.get(prev).getEndTime())>=0){
                maxmeets++;
                prev = i;
            }
        }
        return maxmeets;
    }
}
