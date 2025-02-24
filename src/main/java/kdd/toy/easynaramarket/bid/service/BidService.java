package kdd.toy.easynaramarket.bid.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kdd.toy.easynaramarket.bid.dto.BidApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class BidService {

    private final WebClient webClient;

    private final WebClient webClient2;

    @Autowired
    public BidService(@Qualifier("webClient1") WebClient webClient1,
                      @Qualifier("webClient2") WebClient webClient2) {
        this.webClient = webClient1;
        this.webClient2 = webClient2;
    }

    //외부 사용자도 기능테스트를 해볼 수 있도록 서비스키를 공개함
    private String decodedKey = "oVDbi/b97nK+x24paydGSPkBoGyC9qx4m33hq6TgeraM3xFMkP25s2xEUQ6EK/ngi0AFx8r/yT88EBAIV1lxuA==";
    private String encodedKey;

    {
        encodedKey = URLEncoder.encode(decodedKey, StandardCharsets.UTF_8);
    }

    //입찰공고(공사) 목록 조회
    public List<BidApiResponse.Item> fetchConstructionList(String bgDt, String edDt, int pageNo) {

        //uri 생성
        String uri = UriComponentsBuilder.fromPath("/getBidPblancListInfoCnstwk")
                .queryParam("serviceKey", encodedKey)
                .queryParam("pageNo", String.valueOf(pageNo))
                .queryParam("numOfRows", "25")
                .queryParam("inqryDiv", "1")
                .queryParam("inqryBgnDt", bgDt)
                .queryParam("inqryEndDt", edDt)
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

    //입찰공고 단일 상세조회
    public String FetchBidDetail(String number, String order) throws JsonProcessingException {

        //request body 생성
        Map<String, Object> requestBody = Map.of(
                "dmItemMap", Map.of(
                        "bidPbancNo", number,
                        "bidPbancOrd", order
                )
        );


        String response = webClient2.post()
                .uri("/pn/pnp/pnpe/facilBidPbac/selectFacilAnncMngV.do")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(response);

        return response;
    }
}