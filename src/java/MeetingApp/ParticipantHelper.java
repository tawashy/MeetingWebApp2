/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingApp;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author mimi
 */
public class ParticipantHelper {
    
    Session session = null;
    
    public ParticipantHelper(){
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List getParticipants(){
        List<Participant> participantList = null;
        
        String sql = "select * from participant";
        
        try {
            if (!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            q.addEntity(Participant.class);
            
            participantList = (List<Participant>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();;
        }
        
        return participantList;
    }
        public int insertParticipant(Participant a){
                int result = 0;
        
        String sql = "insert into participant(PARTICIPANT_EMAIL, PARTICIPANT_NAME)" 
                + "values (:email, :name)";
        
        try {
            // starting a transaction if on wisn't active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            
            // creating an actul query that can be executed
            SQLQuery q = session.createSQLQuery(sql);
            //associating our Avtor POJO and table with the query 
            q.addEntity(Host.class);
            
            // binding values to the placeholders in the query
            q.setParameter("name", a.getParticipantName());
            q.setParameter("email", a.getParticipantEmail());
            
            // executing the query 
            result = q.executeUpdate();
            
            // commiting the query to the database
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
        
        public int searchParticipant(Participant a){
        
        List<Participant> participant = null;
        
        // create the query, but as a String
        String sql = "select * from Participant where PARTICIPANT_EMAIL like :email and PARTICIPANT_NAME like :name";
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            
            // associate the Category POJO and table with the query 
            q.addEntity(Participant.class);
            
            q.setParameter("name", a.getParticipantName());
            q.setParameter("email", a.getParticipantEmail());
            
            // execute the query and cast the returned List
            // as a List of Films
            participant = (List<Participant>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return participant.size();
    }
    
}