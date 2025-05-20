package com.example.EncyclopediaSearch.service.imp;

import com.example.EncyclopediaSearch.EncyclopediaSearchApplication;
import com.example.EncyclopediaSearch.client.EncyclopediaClient;
import com.example.EncyclopediaSearch.dto.EncyclopediaResultDto;
import com.example.EncyclopediaSearch.exception.EncyclopediaApiException;
import com.example.EncyclopediaSearch.service.EncyclopediaService;
import com.example.EncyclopediaSearch.vo.EncyclopediaSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
@Slf4j
@Service
public class EncyclopediaSearchImp implements EncyclopediaService {
    private final EncyclopediaClient encyclopediaClient;

    public EncyclopediaSearchImp(EncyclopediaClient encyclopediaClient) {
        this.encyclopediaClient = encyclopediaClient;
    }

    @Override
    public Mono<List<EncyclopediaResultDto>> searchEncyclopedia(String keyword){
        return encyclopediaClient.searchEncyclopedias(keyword)
                .flatMapMany(resp -> {
                    if (resp.getItems() == null) {
                        return Flux.error(new EncyclopediaApiException("No items returned"));
                    }
                    return Flux.fromIterable(resp.getItems());
                })
                // 1) 기본 필드 매핑
                .map(item -> {
                    String title = item.getTitle();
                    String link = item.getLink();
                    String description  = item.getDescription();
                    String thumbnail = Optional.ofNullable(item.getThumbnail()).orElse("");
                    return new EncyclopediaResultDto(title, link, description, thumbnail);
                })
                .collectList();
    }
}
