package com.example.SplitMate.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private long amount;
    private Currenccy currency;
    private String Description;
    private String date;
    @ManyToOne
    private Group group;
}
