package com.multimodule.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "userform")
public class User {
    @Id
    private Long phone;
    private String name;
    private int age;
    private String address;
    private String email;
    private String education;
    private String school;
    private String parentsName;
    private double percentage;
    private String createdDate;
    private String updatedDate;
    private String status;
    private String statusMessage;
    private String updateMessage;
    private int updateCount;

    public User(){
        this.updateCount = 0;
    }
}
