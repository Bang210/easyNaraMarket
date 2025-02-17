package kdd.toy.easynaramarket.bid.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BidResponseDto {

    //입찰공고번호
    private String bidPbancNo;
    private String bidPbancOrd;
    //공고명
    private String bidNtceNm;
    //게시일시
    private LocalDateTime bidNtceDt;
    //입찰마감일시
    private LocalDateTime bidClseDt;
    //공고기관
    private String ntceInsttNm;
    //수요기관
    private String dminsttNm;

}
