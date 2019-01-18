package com.datacenter.router;

import com.datacenter.handler.DailyHandler;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author fuchun
 * @date 2019-01-17
 */
@Configuration
@EnableWebFlux
public class RoutingConfiguration {

  @Bean
  public RouterFunction<ServerResponse> dailyRouter() {
    val dailyRouter = POST("/daily").and(JSON_FORMATTER);
    val sectionRouter = POST("/section").and(JSON_FORMATTER);
    return route(dailyRouter, dailyHandler::queryBasicDailyInfo)
        .andRoute(sectionRouter, dailyHandler::querySectionDailyInfo);
  }

  @Autowired
  private DailyHandler dailyHandler;

  private final RequestPredicate JSON_FORMATTER = accept(APPLICATION_JSON);
}
