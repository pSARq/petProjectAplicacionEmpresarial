package co.com.sofka.questions.usecases;

import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class UseCaseDeleteTest {

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    UseCaseDelete useCaseDelete;


    @BeforeEach
    public void setup(){
        questionRepository = mock(QuestionRepository.class);
        answerRepository = mock(AnswerRepository.class);
        useCaseDelete = new UseCaseDelete(questionRepository, answerRepository);
    }

    @Test
    void deleteQuestionAndAnswer() {

        when(questionRepository.deleteById("1")).thenReturn(Mono.empty());
        when(answerRepository.deleteByQuestionId("1")).thenReturn(Mono.empty());


        StepVerifier.create(useCaseDelete.apply("1"))
                .verifyComplete();

        verify(questionRepository).deleteById("1");
        verify(answerRepository).deleteByQuestionId("1");
    }
}