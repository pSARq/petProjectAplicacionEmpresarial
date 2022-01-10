package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Voto;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.VotoRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseDeleteVoto implements Function<Voto, Mono<String>> {

    private final VotoRepository votoRepository;
    private final AnswerRepository answerRepository;

    public UseCaseDeleteVoto(VotoRepository votoRepository, AnswerRepository answerRepository) {
        this.votoRepository = votoRepository;
        this.answerRepository = answerRepository;
    }


    public Mono<String> apply(Voto voto) {
        return votoRepository.deleteById(voto.getId())
                .then(deshacerVoto(voto));
    }

    private Mono<String> deshacerVoto(Voto voto) {
        return answerRepository.findById(voto.getAnswerId())
                .flatMap(answer -> {

                    if (voto.getVoto() == 1) {
                        answer.setPosition(answer.getPosition() - 1);
                    }

                    //guarda y retorna id del voto
                    return answerRepository.save(answer)
                            .map(Answer::getId);
                });
    }
}
