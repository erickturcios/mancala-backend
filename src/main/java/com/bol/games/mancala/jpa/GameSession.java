package com.bol.games.mancala.jpa;



import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sessions")

public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameSession that = (GameSession) o;

        if (!id.equals(that.id)) return false;
        if (!sessionId.equals(that.sessionId)) return false;
        if (!Objects.equals(createdOn, that.createdOn)) return false;
        return Objects.equals(updatedOn, that.updatedOn);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + sessionId.hashCode();
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (updatedOn != null ? updatedOn.hashCode() : 0);
        return result;
    }

    
    @PrePersist
    void setCreationDate(){
        this.createdOn = new Date();
    }

    @PreUpdate
    void setUpdateDate(){
        this.updatedOn = new Date();
    }
}
