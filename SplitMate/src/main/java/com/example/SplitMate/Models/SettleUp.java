package com.example.SplitMate.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SettleUp extends BaseModel{
    @OneToOne
    private User user;
    private String owes;
    private String gives;
}
