package kdd.toy.easynaramarket.bid.service;

import kdd.toy.easynaramarket.bid.dto.BidResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {

    private final WebClient webClient;

    public List<BidResponseDto> fetchConstructionList() {

         Mono<List<BidResponseDto>> bidResponseDto = webClient.get()
                .uri("/getBidPblancListInfoCnstwk?serviceKey=oVDbi%2Fb97nK%2Bx24paydGSPkBoGyC9qx4m33hq6TgeraM3xFMkP25s2xEUQ6EK%2Fngi0AFx8r%2FyT88EBAIV1lxuA%3D%3D&pageNo=1&numOfRows=10&inqryDiv=1&inqryBgnDt=202001010000&type=json&inqryEndDt=202001302359")
                .retrieve()
                .bodyToMono(ResponseWrapper.class)
                .block();
    }
}