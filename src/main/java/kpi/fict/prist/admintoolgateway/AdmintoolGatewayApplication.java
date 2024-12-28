package kpi.fict.prist.admintoolgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdmintoolGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmintoolGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
										   @Value("${services.url.admintool-core}") String admintoolCoreUrl,
										   @Value("${services.url.admintool-frontend}") String admintoolFrontendUrl) {
		return builder.routes()
			.route("admin-core", r -> r.order(10)
				.path("/api/**")
				.filters(filters -> filters.stripPrefix(1))
				.uri(admintoolCoreUrl)
			)
			.route("admin-frontend", r -> r.order(20)
				.path("/**")
				.uri(admintoolFrontendUrl)
			)
			.build();
	}


}
