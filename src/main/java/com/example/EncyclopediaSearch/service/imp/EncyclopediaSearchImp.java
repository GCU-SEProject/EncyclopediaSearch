package com.example.EncyclopediaSearch.service.imp;

import com.example.EncyclopediaSearch.EncyclopediaSearchApplication;
import com.example.EncyclopediaSearch.client.EncyclopediaClient;
import com.example.EncyclopediaSearch.dto.EncyclopediaResultDto;
import com.example.EncyclopediaSearch.exception.EncyclopediaApiException;
import com.example.EncyclopediaSearch.service.EncyclopediaService;
import com.example.EncyclopediaSearch.vo.EncyclopediaSearchResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
                    String title = item.getSnippet().getTitle();
                    String link = item.getSnippet().getLink();
                    String description  = item.getSnippet().getDescription();
                    String thumbnail  = Optional.of(item.getSnippet())
                            .map(EncyclopediaSearchResponse.Snippet::getThumbnail)
                            .orElse("");
                    return new EncyclopediaResultDto(title, link, description, thumbnail,null, null, null);
                })
                .collectList();
    }


}
