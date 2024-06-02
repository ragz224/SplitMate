package com.example.SplitMate.DTOs.ExpenseDto;

import com.example.SplitMate.Models.Currenccy;
import com.example.SplitMate.Models.Expense;
import com.example.SplitMate.Models.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseRequestDto {
    private Group group;
    private Expense expense;
    private Currenccy currenccy;
    private String Date;

}
