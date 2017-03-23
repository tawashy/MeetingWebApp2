/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingApp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author user
 */
@Named(value = "meetingController")
@SessionScoped
public class MeetingController implements Serializable {

    int startId;
    String name;
    String description;
    Date date;
    Date time;
    String location;
    String response;
    Host host;
    int selectedId;
    
    DataModel meetingName;
    
    private int recordCount;
    private int pageSize = 5;
    private Meeting selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResponse() {
        // if the firstname and lastname components in the actor.xhtml
        // have data in them, then insert it into the database
        if (name != null && description != null && date != null && time != null && location != null) {

            // initialize an actor so that it contains the data
            // input in the actor.xhtml
            meeting = new Meeting(host, name, description, date, time, location);

            // call the helper method that inserts the data into
            // the database
            if (helper.insertMeeting(meeting) == 1) {
                // if the insert works
                name = null;
                description = null;
                date = null;
                time = null;
                location = null;
                
                response = "Meeting Added.";
                return response;
            } else {
                // if the insert doesn't work
                name = null;
                description = null;
                date = null;
                time = null;
                location = null;
                
                response = "Meeting Not Added.";
                return response;
            }
        } else {
            // if nothing was input into the form
            //don't display a message 
            response = " ";
            return response;
        }
    }

    public void setResponse(String response) {
        this.response = response;
    }

    // this is our class that uses Hibernate to query the database
    MeetingHelper helper;

    // this is our Actor POJO
    Meeting meeting;
    
    public MeetingController() {
        
        helper = new MeetingHelper();
        startId = 0;
        recordCount = helper.getNumberMeeting();
        
    }
    
    public String next(){
        startId = startId + (pageSize + 1);
        recreateModel();
        return "updateMeeting";
    }
    
    public DataModel getMeetingName() {
        if (meetingName == null){
            meetingName = new ListDataModel(helper.getMeetingName(startId));
        }
        return meetingName;
    }

    public void setMeetingName(DataModel meetingName) {
        this.meetingName = meetingName;
    }
    
    private void recreateModel(){
        meetingName = null;
        recordCount = helper.getNumberMeeting();
    }
    
    public String previous(){
        startId = startId - pageSize;
        recreateModel();
        return "updateMeeting";
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
    
    public String prepareView(){
        // get all of the data associated with the selected movie
        selected = (Meeting) getMeetingName().getRowData();
        // return the name of the page that will load when the hyperlink
        // is selected
        return "browse";
    }
    
    public Meeting getSelected() {
        if (selected == null){
            selected = new Meeting();
        }
        return selected;
    }

    public void setSelected(Meeting selected) {
        this.selected = selected;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }
    
    
}
