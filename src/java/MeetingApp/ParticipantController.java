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
 * @author mimi
 */
@Named(value = "participantController")
@SessionScoped
public class ParticipantController implements Serializable {

    // these fields map directly to components in the participant.xhtml
    int id;
    String name;
    String email;
    String response;
    
    DataModel participants;
    
    
    // this is our class that uses Hibernare to query the host table
    ParticipantHelper helper;
    
    // this is our Host POJO
    Participant participant;
    
    /**
     * Creates a new instance of ParticipantController
     */
    public ParticipantController() {
        helper = new ParticipantHelper();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getResponse() {
            if (name != null && email != null) {
            
            
            // initializing an actor
            participant = new Participant(email, name);
            
            //calling our helper that inserts a row into the actor table
            if (helper.insertParticipant(participant) == 1 ){
                // insert was successful
                name = null;
                email = null;
                response = "Participant Added.";
                return response;
            } else {
                // inser failed
                name = null;
                email = null;
                response = "Participant Not Added.";
            }
        } else {
            // don't display a message when the user hasn't input 
            // a name and email
            response = " ";
        }
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ParticipantHelper getHelper() {
        return helper;
    }

    public void setHelper(ParticipantHelper helper) {
        this.helper = helper;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setparticipant(Participant participant) {
        if (participants == null){
            participants = new ListDataModel(helper.getParticipants());
        }
        this.participant = participant;
    }

    public DataModel getParticipants() {
        return participants;
    }

    public void setParticipants(DataModel participants) {
        this.participants = participants;
    }
    
    public String login(){

         if (name != null && email != null) {
            
            // initializing an actor
            participant = new Participant(email, name);
            
            //calling our helper that inserts a row into the actor table
            if (helper.searchParticipant(participant) == 1 ){
                // insert was successful
                email = null;
                name = null;
                return "participantMeeting";
                
            } else {
                // inser failed
                name = null;
                email = null;
                 return "participant_login";
            }
        } else {
            // don't dis[lay a message when the user hasn't input 
            // a first and last name
            return "participant_login";
        }
        
    }
    
}