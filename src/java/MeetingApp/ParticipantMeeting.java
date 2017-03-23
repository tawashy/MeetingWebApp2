package MeetingApp;
// Generated 12/03/2017 11:53:04 م by Hibernate Tools 4.3.1



/**
 * ParticipantMeeting generated by hbm2java
 */
public class ParticipantMeeting  implements java.io.Serializable {


     private Integer participantMeetingId;
     private Meeting meeting;
     private Participant participant;
     private Status status;

    public ParticipantMeeting() {
    }

    public ParticipantMeeting(Meeting meeting, Participant participant, Status status) {
       this.meeting = meeting;
       this.participant = participant;
       this.status = status;
    }
   
    public Integer getParticipantMeetingId() {
        return this.participantMeetingId;
    }
    
    public void setParticipantMeetingId(Integer participantMeetingId) {
        this.participantMeetingId = participantMeetingId;
    }
    public Meeting getMeeting() {
        return this.meeting;
    }
    
    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
    public Participant getParticipant() {
        return this.participant;
    }
    
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
    public Status getStatus() {
        return this.status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }




}


