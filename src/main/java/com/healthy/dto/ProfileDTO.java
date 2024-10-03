package com.healthy.dto;

import com.healthy.model.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDTO {
    private String name;
    private Float height;
    private Float weight;
    private Integer age;
    private Gender gender;
    private String healthConditions;

    private List<GoalDTO> goals;
}
