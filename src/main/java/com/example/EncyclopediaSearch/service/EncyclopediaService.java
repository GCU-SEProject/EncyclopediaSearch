package com.example.EncyclopediaSearch.service;


import com.example.EncyclopediaSearch.dto.EncyclopediaResultDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EncyclopediaService {
    Mono<List<EncyclopediaResultDto>> searchEncyclopedia(String keyword);

}
