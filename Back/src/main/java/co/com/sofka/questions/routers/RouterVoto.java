package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.VotoDTO;
import co.com.sofka.questions.usecases.UseCaseVotar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterVoto {

    @Bean
    public RouterFunction<ServerResponse> votar(UseCaseVotar useCaseVotar){
        return route(POST("/votar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(VotoDTO.class)
                        .flatMap(votoDTO -> useCaseVotar.apply(votoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.TEXT_PLAIN)
                                        .bodyValue(result)))
                        .onErrorResume(throwable -> ServerResponse.badRequest().body(throwable.getMessage(), String.class))
        );
    }
}
