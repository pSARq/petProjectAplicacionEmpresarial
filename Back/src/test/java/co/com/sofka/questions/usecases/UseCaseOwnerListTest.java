package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class UseCaseOwnerListTest {

    QuestionRepository repository;
    UseCaseOwnerList useCaseOwnerList;


    @BeforeEach
    public void setup(){
        MapperUtil mapperUtils = new MapperUtil();
        repository = mock(QuestionRepository.class);
        useCaseOwnerList = new UseCaseOwnerList(repository, mapperUtils);
    }

    @Test
    void getOwnerListById() {
        var question = new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");

        when(repository.findByUserId("xxxx-xxxx")).thenReturn(Flux.just(question));

        StepVerifier.create(useCaseOwnerList.apply("xxxx-xxxx"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getType().equals("tech");
                    return true;
                })
                .verifyComplete();

        verify(repository).findByUserId("xxxx-xxxx");
    }
}