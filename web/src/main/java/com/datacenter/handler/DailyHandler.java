package com.datacenter.handler;

import com.datacenter.message.DailyReq;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author fuchun
 */
@Component
public class DailyHandler {

  /**
   * @param request
   * @return
   */
  public Mono<ServerResponse> queryBasicDailyInfo(ServerRequest request) {
    return request.bodyToMono(DailyReq.class).flatMap(body -> {
      final String date = body.getDate();
      final List<String> stores = body.getStores();
      Map<String, Object> data = Maps.newHashMap();
      data.put("date", date);
      data.put("stores", stores);
      return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(data));
    });
  }

  /**
   * @param request
   * @return
   */
  public Mono<ServerResponse> querySectionDailyInfo(ServerRequest request) {
    return request.bodyToMono(DailyReq.class).flatMap(body -> {
      final String date = body.getDate();
      final List<String> stores = body.getStores();
      Map<String, Object> data = Maps.newHashMap();
      data.put("date", date);
      data.put("stores", stores);
      return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(data));
    });
  }


}
