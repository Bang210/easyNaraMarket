package kdd.toy.easynaramarket.bid.controller;

import kdd.toy.easynaramarket.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    @GetMapping("/getBidList")
    public String getBidList() {
        return bidService.fetchConstructionList();
    }
}
