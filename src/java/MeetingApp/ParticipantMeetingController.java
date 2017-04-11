/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingApp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author user
 */
@Named(value = "participantMeetingController")
@SessionScoped
public class ParticipantMeetingController implements Serializable {

    
    DataModel participantMeetingList;
    DataModel meetingList;
    ParticipantMeetingHelper helper;

    //POJO
    ParticipantMeeting participantMeeting;
    Meeting meeting;
    Status status;
    Participant participant;
    Host host;

    // Data passed from another controller
    int meetingId;
    int statusId;
    String email;
    String hostEmail;
    

    // 
    String response;
    String statusRespone;
    private int recordCount;
    private int pageSize = 5;
    private ParticipantMeeting selected;

    
    int selectedId;
    int startId;
    
    
    /**
     * Creates a new instance of ParticipantMeetingController
     */
    public ParticipantMeetingController() {
        helper = new ParticipantMeetingHelper();
        startId = 0;
    }

    public String getResponse() {
        if (meeting.getMeetingId() != 0 && email != null) {
            
            // create objects
            // if number of participant is greater than 5 ... Reject the insertion.
            //if (helper.getNumberparticipant(meeting.getMeetingId())   ){
                
            //}
            participant = new Participant(email, null);
            status = new Status(statusId, null);
            
            // initializing an actor
            participantMeeting = new ParticipantMeeting(meeting, participant, status);
            
            //calling our helper that inserts a row into the actor table
            if (helper.inviteParticipant(participantMeeting) == 1 ){
                // insert was successful
                meeting = null;
                participant = null;
                email = null;
                status = null;
                response = "participant Added to meeting.";
                return response;
            } else {
                // inser failed
                meeting = null;
                participant = null;
                email = null;
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

    public DataModel getMeetingList() {
        if (meetingList == null){
            meetingList = new ListDataModel(helper.getMeetingName(0, email));
        }
        
        return meetingList;
    }

    public void setMeetingList(DataModel meetingList) {
        this.meetingList = meetingList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public boolean isHasNextPage(){
        if (startId + pageSize < recordCount){
            return true;
        }
        return false;
    }
    
    public boolean isHasPreviousPage(){
        if (startId - pageSize > 0){
            return true;
        }
        return false;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public String prepareView(){
        // get all of the data associated with the selected movie
        selected = (ParticipantMeeting) getMeetingList().getRowData();
        // return the name of the page that will load when the hyperlink
        // is selected
        return "par_meeting_details";
    }
    
    public ParticipantMeeting getSelected() {
        if (selected == null){
            selected = new ParticipantMeeting();
        }
        return selected;
    }

    public void setSelected(ParticipantMeeting selected) {
        this.selected = selected;
    }
    
    

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public DataModel getParticipantMeetingList() {
        if (participantMeetingList == null){
            participantMeetingList = new ListDataModel(helper.getParticipants(meeting.getMeetingId()));
        }
        return participantMeetingList;
    }

    public void setParticipantMeetingList(DataModel participantMeetingList) {
        this.participantMeetingList = participantMeetingList;
    }

    public String getStatusRespone() {
        if (meeting.getMeetingId() != 0 && email != null) {
            
            // create objects
            // if number of participant is greater than 5 ... Reject the insertion.
            //if (helper.getNumberparticipant(meeting.getMeetingId())   ){
                
            //}
            participant = new Participant(email, null);
            status = new Status(statusId, null);
            
            // initializing an actor
            participantMeeting = new ParticipantMeeting(meeting, participant, status);
            
            //calling our helper that inserts a row into the actor table
            if (helper.inviteParticipant(participantMeeting) == 1 ){
                // insert was successful
                meeting = null;
                participant = null;
                email = null;
                status = null;
                response = "Status Update.";
                return response;
            } else {
                // inser failed
                meeting = null;
                participant = null;
                email = null;
                status = null;
                response = "Status Not Update.";
            }
        } else {
            // don't dis[lay a message when the user hasn't input 
            // a first and last name
            response = " ";
        }
        return response;
    }

    public void setStatusRespone(String response) {
        this.response = response;
    }
    
    
}
