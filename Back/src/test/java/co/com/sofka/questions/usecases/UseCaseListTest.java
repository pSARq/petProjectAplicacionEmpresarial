package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class UseCaseListTest {

    QuestionRepository repository;
    UseCaseList useCaseList;


    @BeforeEach
    public void setup(){
        MapperUtil mapperUtils = new MapperUtil();
        repository = mock(QuestionRepository.class);
        useCaseList = new UseCaseList(repository, mapperUtils);
    }

    @Test
    void getAllValidationTest() {
        var question = new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");
        when(repository.findAll()).thenReturn(Flux.just(question));

        StepVerifier.create(useCaseList.get())
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getType().equals("tech");
                    return true;
                })
                .verifyComplete();

        verify(repository).findAll();
    }
}