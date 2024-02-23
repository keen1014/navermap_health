package com.example.moagym;

public class ItemData {
    private int imageResId;
    private String itemName;
    private int itemId; // 아이템 식별자 추가

    public ItemData(int imageResId, String itemName, int itemId) {
        this.imageResId = imageResId;
        this.itemName = itemName;
        this.itemId = itemId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemId() {
        return itemId;
    }
}

