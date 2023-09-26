package com.bol.games.mancala.jpa;

import jakarta.persistence.*;


import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "configurations")

public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "game_session")
    private String gameSession;

    @Column(name = "number_of_stones")
    private Integer numberOfStones;

    @Column(name = "step_back_allowed")
    private Boolean stepBackAllowed;

    @Column(name = "auto_rotate")
    private Boolean autorotate;

    @Column(name = "alias1")
    private String alias1;

    @Column(name = "alias2")
    private String alias2;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Configuration() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameSession() {
        return gameSession;
    }

    public void setGameSession(String gameSession) {
        this.gameSession = gameSession;
    }

    public Integer getNumberOfStones() {
        return numberOfStones;
    }

    public void setNumberOfStones(Integer numberOfStones) {
        this.numberOfStones = numberOfStones;
    }

    public Boolean getStepBackAllowed() {
        return stepBackAllowed;
    }

    public void setStepBackAllowed(Boolean stepBackAllowed) {
        this.stepBackAllowed = stepBackAllowed;
    }

    public Boolean getAutorotate() {
        return autorotate;
    }

    public void setAutorotate(Boolean autorotate) {
        this.autorotate = autorotate;
    }

    public String getAlias1() {
        return alias1;
    }

    public void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    public String getAlias2() {
        return alias2;
    }

    public void setAlias2(String alias2) {
        this.alias2 = alias2;
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

        Configuration that = (Configuration) o;

        if (!id.equals(that.id)) return false;
        if (!gameSession.equals(that.gameSession)) return false;
        if (!Objects.equals(numberOfStones, that.numberOfStones))
            return false;
        if (!Objects.equals(stepBackAllowed, that.stepBackAllowed))
            return false;
        if (!Objects.equals(autorotate, that.autorotate)) return false;
        if (!Objects.equals(alias1, that.alias1)) return false;
        if (!Objects.equals(alias2, that.alias2)) return false;
        if (!Objects.equals(createdOn, that.createdOn)) return false;
        return Objects.equals(updatedOn, that.updatedOn);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + gameSession.hashCode();
        result = 31 * result + (numberOfStones != null ? numberOfStones.hashCode() : 0);
        result = 31 * result + (stepBackAllowed != null ? stepBackAllowed.hashCode() : 0);
        result = 31 * result + (autorotate != null ? autorotate.hashCode() : 0);
        result = 31 * result + (alias1 != null ? alias1.hashCode() : 0);
        result = 31 * result + (alias2 != null ? alias2.hashCode() : 0);
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
