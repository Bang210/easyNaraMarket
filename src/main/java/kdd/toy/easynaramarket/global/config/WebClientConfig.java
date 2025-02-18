package kdd.toy.easynaramarket.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("https://apis.data.go.kr/1230000/ad/BidPublicInfoService");
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        return WebClient.builder()
                .baseUrl("https://apis.data.go.kr/1230000/ad/BidPublicInfoService")
                .uriBuilderFactory(factory)
                .build();
    }

    @Bean
    public WebClient webClient2() {

        return WebClient.builder()
                .baseUrl("https://www.g2b.go.kr")
                .build();
    }
}