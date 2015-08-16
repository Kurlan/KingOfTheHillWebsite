package com.kingofthehill.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "grafiti")
public class Grafiti implements Serializable {

    public Grafiti() {

    }

    private static final long serialVersionUID = -5006452113005182757L;

    public static final Grafiti EMPTY = new Grafiti();

    @Id
    @Column(name="grafitiid")
    private String grafitiId;

    @Column(name="s3key")
    private String s3Key;

    @Column(name="alttext")
    private String altText;

    @Column(name="urllink")
    private String urlLink;
    
    @Column(name="title")
    private String title;

    @Column(name="email")
    private String email;

    @Column(name="status")
    private String status;
   
    @Column(name="created")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;
    
    @Column(name="modified")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modified;
    
    @Column(name="completed")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime completed;
    
    @Column(name="queue")
    private String queue;
}
