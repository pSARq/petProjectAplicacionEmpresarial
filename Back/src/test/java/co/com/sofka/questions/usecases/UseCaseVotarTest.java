package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Voto;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.VotoDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UseCaseVotarTest {

    VotoRepository votoRepository;
    AnswerRepository answerRepository;
    UseCaseVotar useCaseVotar;
    UseCaseDeleteVoto useCaseDeleteVoto;


    @BeforeEach
    public void setup(){
        MapperUtil mapperUtils = new MapperUtil();
        votoRepository = mock(VotoRepository.class);
        answerRepository = mock(AnswerRepository.class);
        useCaseVotar = new UseCaseVotar(votoRepository, answerRepository, mapperUtils, useCaseDeleteVoto);
    }

    @Test
    void votar() {

        Voto voto = new Voto();
        voto.setId("idVoto");
        voto.setUserId("idUser");
        voto.setQuestionId("idQuestion");
        voto.setAnswerId("idAnswer");

        VotoDTO dto = new VotoDTO();
        dto.setId("idVoto");
        dto.setUserId("idUser");
        dto.setQuestionId("idQuestion");
        dto.setAnswerId("idAnswer");

        Answer answer = new Answer();
        answer.setId("idAnswer");
        answer.setQuestionId("idQuestion");
        answer.setAnswer("respuesta");
        answer.setPosition(0);

        when(answerRepository.findById(voto.getAnswerId())).thenReturn(Mono.just(answer));
        when(answerRepository.save(any())).thenReturn(Mono.just(answer));

        when(votoRepository.findByQuestionIdAndUserId(voto.getQuestionId(), voto.getUserId()))
                .thenReturn(Mono.empty());
        when(votoRepository.save(any())).thenReturn(Mono.just(voto));


        StepVerifier.create(useCaseVotar.apply(dto))
                .expectNextMatches(id -> {
                    assert id.equals("idAnswer");
                    return true;
                })
                .verifyComplete();

        verify(answerRepository).save(any());
        verify(answerRepository).findById(voto.getAnswerId());
        verify(votoRepository).save(any());
        verify(votoRepository).findByQuestionIdAndUserId(voto.getQuestionId(), voto.getUserId());
    }
}