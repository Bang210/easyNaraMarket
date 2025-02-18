package kdd.toy.easynaramarket.bid.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BidApiResponse {

    private Response response;

    @Getter
    @Setter
    public static class Response {

        private Body body;

        @Getter
        @Setter
        public static class Body {

            private List<Item> items;

        }
    }

    @Getter
    @Setter
    public static class Item {

        //입찰공고번호
        private String bidNtceNo;
        private String bidNtceOrd;
        //공고명
        private String bidNtceNm;
        //게시일시
        private String bidNtceDt;
        //입찰마감일시
        private String bidClseDt;
        //공고기관
        private String ntceInsttNm;
        //수요기관
        private String dminsttNm;

    }
}
