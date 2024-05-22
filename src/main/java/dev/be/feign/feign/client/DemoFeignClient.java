package dev.be.feign.feign.client;

import dev.be.feign.common.dto.BaseRequestInfo;
import dev.be.feign.common.dto.BaseResponseInfo;
import dev.be.feign.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "demo-client", // application.yaml에 설정해 놓은 값을 참조
        url = "${feign.url.prefix}", // application.yaml에 설정해 놓은 값을 참조 (= http://localhost:8080/target_server)
        configuration = DemoFeignConfig.class)
public interface DemoFeignClient {
    @GetMapping("/get") // "${feign.url.prefix}/get"으로 요청
    ResponseEntity<BaseResponseInfo> callGet(@RequestHeader("CustomHeaderName") String customHeader,
                                             @RequestParam("name") String name,
                                             @RequestParam("age") Long age);

    @PostMapping("/post") // "${feign.url.prefix}/post"로 요청
    ResponseEntity<BaseResponseInfo> callPost(@RequestHeader("CustomHeaderName") String customHeader,
                                              @RequestBody BaseRequestInfo baseRequestInfo);

    @GetMapping("/errorDecoder")
    ResponseEntity<BaseResponseInfo> callErrorDecoder();
}
