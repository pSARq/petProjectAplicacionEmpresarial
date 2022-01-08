package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UseCaseCreateTest {

    QuestionRepository repository;
    UseCaseCreate useCaseCreate;


    @BeforeEach
    public void setup(){
        MapperUtil mapperUtils = new MapperUtil();
        repository = mock(QuestionRepository.class);
        useCaseCreate = new UseCaseCreate(repository, mapperUtils);
    }

    @Test
    void createQuestion() {
        Question question = new Question();
        question.setId("1");
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");

        QuestionDTO DTO = new QuestionDTO();
        DTO.setUserId("xxxx-xxxx");
        DTO.setType("tech");
        DTO.setCategory("software");
        DTO.setQuestion("¿Que es java?");

        when(repository.save(any())).thenReturn(Mono.just(question));

        StepVerifier.create(useCaseCreate.apply(DTO))
                .expectNextMatches(id -> {
                    assert id.equals("1");
                    return true;
                })
                .verifyComplete();

        verify(repository).save(any());
    }

}