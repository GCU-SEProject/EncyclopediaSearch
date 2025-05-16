package com.example.EncyclopediaSearch.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EncyclopediaResultDto {

    private String title;   // 백과사전 표제어
    private String link;    // 백과사전 설명 링크
    private String description; // 백과사전 설명 내용을 요약한 정보
    private String thumbnail; // 백과사전 섬네일 URL
    private String total; // 검색 결과 개수
    private String start; // 검색 시작 위치
    private String display; // 한 번에 표시할 검색 결과 개수

}
