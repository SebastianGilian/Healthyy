package com.healthy.dto;

import com.healthy.model.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class ProfileCreateDTO {
    private Integer userID;
    private String userName;
    private Integer age;
    private Gender gender;
    private String healthConditions;
}
