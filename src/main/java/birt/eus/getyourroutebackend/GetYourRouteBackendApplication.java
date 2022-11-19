package birt.eus.getyourroutebackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "GetYourRoute"))
public class GetYourRouteBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(GetYourRouteBackendApplication.class, args);
  }

}

