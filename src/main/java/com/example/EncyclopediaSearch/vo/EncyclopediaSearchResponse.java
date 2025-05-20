package com.example.EncyclopediaSearch.vo;

import lombok.Data;

import java.util.List;

@Data
public class EncyclopediaSearchResponse {
    private List<Item> items;

    @Data
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String thumbnail;
    }
}