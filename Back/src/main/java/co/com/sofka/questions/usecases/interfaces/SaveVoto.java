package co.com.sofka.questions.usecases.interfaces;

import co.com.sofka.questions.model.VotoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveVoto {
    Mono<String> apply(@Valid VotoDTO votoDTO);
}
