package kdd.toy.easynaramarket.bid.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kdd.toy.easynaramarket.bid.dto.BidApiResponse;
import kdd.toy.easynaramarket.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    @GetMapping("/getBidList")
    public List<BidApiResponse.Item> getBidList(
            @RequestParam(value = "bgDt", defaultValue = "202502180000") String bgDt,
            @RequestParam(value = "edDt", defaultValue = "202502182359") String edDt,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) throws JsonProcessingException {
        return bidService.fetchConstructionList(bgDt,edDt, pageNo, "cnstwk");
    }

    @GetMapping("/getBidDetail")
    public String getBidDetail() throws JsonProcessingException {
        return bidService.FetchBidDetail("20191243276", "000");
    }

}
