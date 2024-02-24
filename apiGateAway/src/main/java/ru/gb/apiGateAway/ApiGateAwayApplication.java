package ru.gb.apiGateAway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGateAwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateAwayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("Payment",r->r.path("/account/**")
						.uri("http://localhost:8082/"))
				.route("Warehouse",r->r.path("/warehouse/**")
						.uri("http://localhost:8083/")).build();}
}
