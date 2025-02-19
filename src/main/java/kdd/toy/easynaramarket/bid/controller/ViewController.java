package kdd.toy.easynaramarket.bid.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kdd.toy.easynaramarket.bid.dto.BidApiResponse;
import kdd.toy.easynaramarket.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final BidService bidService;

    @GetMapping("/detail")
    public String showDetail(
            @RequestParam(value = "bidNtceNo") String bidNtceNo,
            @RequestParam(value = "bidNtceOrd", defaultValue = "000") String bidNtceOrd,
            Model model
    ) throws JsonProcessingException {
        String bidDetailData = bidService.FetchBidDetail(bidNtceNo, bidNtceOrd);
        model.addAttribute("bidData", bidDetailData);
        model.addAttribute("bidNtceNo", bidNtceNo);
        model.addAttribute("bidNtceOrd", bidNtceOrd);
        return "bid/bid_detail";
    }

    @GetMapping("/list")
    public String bidList(
            @RequestParam(value = "bgDt", defaultValue = "202502180000") String bgDt,
            @RequestParam(value = "edDt", defaultValue = "202502182359") String edDt,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            Model model) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        List<BidApiResponse.Item> bidList = bidService.fetchConstructionList(bgDt,edDt, pageNo);
        model.addAttribute("bidList", bidList);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("bgDt", bgDt);
        model.addAttribute("edDt", edDt);
        return "bid/bid_list";
    }
}
