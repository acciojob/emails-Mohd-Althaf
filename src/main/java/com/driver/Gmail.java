package com.driver;

import java.util.ArrayList;
import java.util.Date;



public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    int Inboxsize=0,Trashsize=0;
    ArrayList<Emailmsg> Inbox = new ArrayList<>();
    ArrayList<Emailmsg> Trash = new ArrayList<>();

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inboxsize>inboxCapacity){
            Trash.add(Inbox.get(0));
            Inbox.remove(0);
            Trashsize++;
            Inboxsize--;
        }
        Inbox.add(new Emailmsg(message,date,sender));
        Inboxsize++;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<Inboxsize;i++){
            if(Inbox.get(i).message.equals(message)){
                Emailmsg deleted = Inbox.remove(i);
                Trash.add(deleted);
                Trashsize++;
                Inboxsize--;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inboxsize==0)
            return "null";
        return Inbox.get(Inboxsize-1).message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inboxsize==0)
            return "null";
        return Inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(int i=0;i<Inboxsize;i++){
            Emailmsg msg = Inbox.get(i);
            if(msg.Date.after(start) && msg.Date.before(end))
                count++;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inboxsize;
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trashsize;
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
        Trashsize = 0;
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
    class Emailmsg{
        String message;
        Date Date;
        String sender;

        public Emailmsg(String message, java.util.Date date, String sender) {
            this.message = message;
            Date = date;
            this.sender = sender;
        }
    }
}
