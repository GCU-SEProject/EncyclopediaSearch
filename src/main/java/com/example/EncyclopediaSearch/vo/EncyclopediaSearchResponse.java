package com.example.EncyclopediaSearch.vo;

import lombok.Data;

import java.util.List;

@Data
public class EncyclopediaSearchResponse {
    private String lastBuildDate;
    private int total; // 전체 검색 결과 개수
    private int start; // 요청 시작 인덱스
    private int display; // 응답에 포함된 아이템의 개수
    private List<Item> items;

    @Data
    public static class Item {
        private String title; // 제목
        private String link; // 상세 링크
        private String description; // 요약 설명
        private String thumbnail; // 섬네일 URL
    }

}