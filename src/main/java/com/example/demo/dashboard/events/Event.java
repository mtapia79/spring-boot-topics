package com.example.demo.dashboard.events;

import java.sql.Timestamp;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.CascadeType;

@Entity
public class Event implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @OneToOne(cascade = {CascadeType.ALL})
    @JsonFormat(shape=JsonFormat.Shape.OBJECT)
    private Actor actor;
    @OneToOne(cascade = {CascadeType.ALL})
    @JsonFormat(shape=JsonFormat.Shape.OBJECT)
    private Repo repo;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Timestamp created_at;


    public Event() {
    }

    public Event(Long id, String type,  Timestamp createdAt) {
        this.id = id;
        this.type = type;
        this.created_at = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}

