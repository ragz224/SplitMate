package com.example.SplitMate.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groupp")
public class Group extends BaseModel{
    @ManyToMany
    private List<User> users;
    private String name;
    private String strtDate;
    private String endDate;
    @ManyToOne
    private User Admin;

}
