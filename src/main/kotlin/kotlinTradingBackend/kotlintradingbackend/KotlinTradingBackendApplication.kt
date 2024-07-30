package kotlinTradingBackend.kotlintradingbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class KotlinTradingBackendApplication

fun main(args: Array<String>) {
	runApplication<KotlinTradingBackendApplication>(*args)
}

@Configuration
class WebConfig : WebMvcConfigurer {

	override fun addCorsMappings(registry: CorsRegistry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8081") // Adjust as needed
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowedHeaders("*")
			.allowCredentials(true)
	}
}
