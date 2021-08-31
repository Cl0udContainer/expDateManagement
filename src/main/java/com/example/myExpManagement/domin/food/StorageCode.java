package com.example.myExpManagement.domin.food;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * REFR : 냉장 보관
 * Freez : 냉동 보관
 * ROOM : 상온
 */
@Data
@AllArgsConstructor
public class StorageCode {
    private String code;
    private String displayName;
}
