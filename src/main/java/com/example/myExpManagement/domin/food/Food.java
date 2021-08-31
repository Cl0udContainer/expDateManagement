package com.example.myExpManagement.domin.food;

import lombok.Data;

@Data
public class Food {
    private Long id;
    private Long memberId;
    private String foodName;
    private String quantityUnit;
    private Integer quantity;
    private String expDate;

    private Boolean alarm; // 알람 여부
    private FoodType foodType; // 식품 종류
    private String storageCode; // 저장 방식
}

