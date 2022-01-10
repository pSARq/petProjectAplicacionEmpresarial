package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Voto;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class UseCaseDeleteVotoTest {

    VotoRepository votoRepository;
    AnswerRepository answerRepository;
    UseCaseDeleteVoto useCaseDeleteVoto;


    @BeforeEach
    public void setup(){
        votoRepository = mock(VotoRepository.class);
        answerRepository = mock(AnswerRepository.class);
        useCaseDeleteVoto = new UseCaseDeleteVoto(votoRepository, answerRepository);
    }

    @Test
    void deleteVoto() {
        Voto voto = new Voto();
        voto.setId("idVoto");
        voto.setQuestionId("idQuestion");
        voto.setAnswerId("idAnswer");
        voto.setVoto(1);

        Answer answer = new Answer();
        answer.setId("idAnswer");
        answer.setUserId("xxxx-xxxx");
        answer.setAnswer("respuesta");
        answer.setPosition(0);
        answer.setQuestionId("1");

        when(votoRepository.deleteById(voto.getId())).thenReturn(Mono.empty());
        when(answerRepository.findById(voto.getAnswerId())).thenReturn(Mono.just(answer));
        when(answerRepository.save(any())).thenReturn(Mono.just(answer));

        StepVerifier.create(useCaseDeleteVoto.apply(voto))
                .consumeNextWith(id -> {
                    id.equals("idAnswer");
                })
                .verifyComplete();

        verify(votoRepository).deleteById("idVoto");
        verify(answerRepository).findById("idAnswer");
        verify(answerRepository).save(any());

    }
}