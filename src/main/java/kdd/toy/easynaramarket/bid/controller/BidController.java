package kdd.toy.easynaramarket.bid.controller;

import kdd.toy.easynaramarket.bid.dto.BidResponseDto;
import kdd.toy.easynaramarket.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    @GetMapping("/getBidList")
    public List<BidResponseDto> getBidList() {
        return bidService.fetchConstructionList();
    }
}
