package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Voto;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.VotoDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.VotoRepository;
import co.com.sofka.questions.usecases.interfaces.SaveVoto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UseCaseVotar implements SaveVoto {

    private final VotoRepository votoRepository;
    private final AnswerRepository answerRepository;
    private final MapperUtil mapperUtil;
    private final UseCaseDeleteVoto useCaseDeleteVoto;

    public UseCaseVotar(VotoRepository votoRepository, AnswerRepository answerRepository, MapperUtil mapperUtil, UseCaseDeleteVoto useCaseDeleteVoto) {
        this.votoRepository = votoRepository;
        this.answerRepository = answerRepository;
        this.mapperUtil = mapperUtil;
        this.useCaseDeleteVoto = useCaseDeleteVoto;
    }

    @Override
    public Mono<String> apply(VotoDTO votoDTO) {
        Objects.requireNonNull(votoDTO);
        Voto voto = mapperUtil.mapperToVoto().apply(votoDTO);
        return votoRepository.findByQuestionIdAndUserId(voto.getQuestionId(), voto.getUserId())
                .flatMap(voto1 -> useCaseDeleteVoto.apply(voto1)
                        .then(guardarVoto(voto, voto1)))
                .switchIfEmpty(guardarVoto(voto, voto));
    }

    private Mono<String> guardarVoto(Voto voto, Voto voto1) {
        return votoRepository.save(voto)
                .then(guardarPuestoPregunta(voto, voto1));
    }

    private Mono<String> guardarPuestoPregunta(Voto voto, Voto voto1) {
        return answerRepository.findById(voto.getAnswerId())
                .flatMap(answer -> {
                    //asigna la posicion según el voto
                    answer.setPosition(answer.getPosition() != 0
                            ? answer.getPosition() + voto.getVoto()
                            : voto.getVoto());

                    //valida que no quede menor a 0
                    if (answer.getPosition() < 0) {
                        answer.setPosition(0);
                    }

                    //guarda y retorna id del voto
                    return answerRepository.save(answer)
                            .map(Answer::getId);
                });
    }

}
