package com.kingofthehill.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Id
    private String grafitiId;

    private String s3Key;

    private String altText;

    private String urlLink;

    private String title;

    private String email;

    private String status;
    
    private Date created;
    
    private Date modified;
    
    private Date completed;
    
    private String queue;
}
