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

@Service
@Validated
public class UseCaseVotar implements SaveVoto {

    private final VotoRepository votoRepository;
    private final AnswerRepository answerRepository;
    private final MapperUtil mapperUtil;

    public UseCaseVotar(VotoRepository votoRepository, AnswerRepository answerRepository, MapperUtil mapperUtil) {
        this.votoRepository = votoRepository;
        this.answerRepository = answerRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public Mono<String> apply(VotoDTO votoDTO) {
        Voto voto = mapperUtil.mapperToVoto().apply(votoDTO);
        return votoRepository.findByQuestionIdAndAnswerIdAndUserId(voto.getQuestionId(), voto.getAnswerId(), voto.getUserId())
                .flatMap(voto1 -> Mono.just("Ya voto"))
                .switchIfEmpty(guardarVoto(voto));
    }

    private Mono<String> guardarVoto(Voto voto) {
        return votoRepository.save(voto)
                    .map(Voto::getId)
                .then(guardarPuestoPregunta(voto));
    }

    private Mono<String> guardarPuestoPregunta(Voto voto) {
        return answerRepository.findById(voto.getAnswerId())
                .flatMap(answer -> {
                    answer.setPosition(answer.getPosition() != null ? answer.getPosition() + voto.getVoto()
                            : voto.getVoto());
                    return answerRepository.save(answer)
                            .map(Answer::getId);
                });
    }

}
