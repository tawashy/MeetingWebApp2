/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingApp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author user
 */
@Named(value = "participantMeetingController")
@SessionScoped
public class ParticipantMeetingController implements Serializable {

    String response;
    
    // helper
    ParticipantMeetingHelper helper;
    
    
    // POJO
    ParticipantMeeting participantMeeting;
    Meeting meeting;
    Status status;
    Participant participant;

    /**
     * Creates a new instance of ParticipantMeetingController
     */
    public ParticipantMeetingController() {
        meeting = new Meeting();
        status = new Status();
        participant = new Participant();
        helper = new ParticipantMeetingHelper();
        
    }

    public ParticipantMeeting getParticipantMeeting() {
        return participantMeeting;
    }

    public void setParticipantMeeting(ParticipantMeeting participantMeeting) {
        this.participantMeeting = participantMeeting;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    
    public String getResponse() {
        if (meeting != null && participant != null && status != null) {
            
            // initializing an actor
            participantMeeting = new ParticipantMeeting(meeting, participant, status);
            
            //calling our helper that inserts a row into the actor table
            if (helper.inviteParticipant(participantMeeting) == 1 ){
                // insert was successful
                meeting = null;
                participant = null;
                status = null;
                response = "participant Added to meeting.";
                return response;
            } else {
                // inser failed
                meeting = null;
                participant = null;
                status = null;
                response = "Participant Not Added to Meeting.";
            }
        } else {
            // don't dis[lay a message when the user hasn't input 
            // a first and last name
            response = " ";
        }
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    
    
}
