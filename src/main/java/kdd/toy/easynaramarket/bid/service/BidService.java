package kdd.toy.easynaramarket.bid.service;

import kdd.toy.easynaramarket.bid.dto.BidApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {

    private final WebClient webClient;

    private String decodedKey = "oVDbi/b97nK+x24paydGSPkBoGyC9qx4m33hq6TgeraM3xFMkP25s2xEUQ6EK/ngi0AFx8r/yT88EBAIV1lxuA==";
    private String encodedKey;

    {
        encodedKey = URLEncoder.encode(decodedKey, StandardCharsets.UTF_8);
    }



    public List<BidApiResponse.Item> fetchConstructionList() {

        //uri 생성
        String uri = UriComponentsBuilder.fromPath("/getBidPblancListInfoCnstwk")
                .queryParam("serviceKey", encodedKey)
                .queryParam("pageNo", "2")
                .queryParam("numOfRows", "10")
                .queryParam("inqryDiv", "1")
                .queryParam("inqryBgnDt", "202001010000")
                .queryParam("inqryEndDt", "202001302359")
                .queryParam("type", "json")
                .build(true)
                .toUriString();

         Mono<List<BidApiResponse.Item>> itemListMono = webClient.get()
                .uri(uri)
                 .accept(MediaType.APPLICATION_JSON)
                 .retrieve()
                .bodyToMono(BidApiResponse.class)
                 .flatMap(res -> {
                     if (res == null) {System.out.println("Api Response null");}
                     if (res.getResponse() == null) {System.out.println(" Response null");}
                     if (res.getResponse().getBody() == null) {System.out.println("Body null");}
                     if (res.getResponse().getBody().getItems() == null) {System.out.println("ItemsList null");}
                     if (res != null && res.getResponse() != null && res.getResponse().getBody() != null) {
                         return Mono.justOrEmpty(res.getResponse().getBody().getItems());
                     }
                     return Mono.empty();  // null이거나 예상한 데이터를 찾을 수 없을 때
                 });

        return  itemListMono.block();
    }
}