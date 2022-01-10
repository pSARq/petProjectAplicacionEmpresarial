package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UseCaseAddAnswerTest {

    AnswerRepository answerRepository;
    UseCaseAddAnswer useCaseAddAnswer;
    UseCaseGet useCaseGet;



    @BeforeEach
    public void setup(){
        MapperUtil mapperUtils = new MapperUtil();
        answerRepository = mock(AnswerRepository.class);
        useCaseGet = mock(UseCaseGet.class);
        useCaseAddAnswer = new UseCaseAddAnswer(mapperUtils, useCaseGet, answerRepository);
    }


    @Test
    void addAnswer() throws Exception {
        Answer answer = new Answer();
        answer.setId("idAnswer");
        answer.setUserId("xxxx-xxxx");
        answer.setAnswer("respuesta");
        answer.setPosition(1);
        answer.setQuestionId("1");

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setUserId("xxxx-xxxx");
        answerDTO.setAnswer("respuesta");
        answerDTO.setPosition(1);
        answerDTO.setQuestionId("1");

        Question question = new Question();
        question.setId("1");
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId("1");
        questionDTO.setUserId("xxxx-xxxx");
        questionDTO.setType("tech");
        questionDTO.setCategory("software");
        questionDTO.setQuestion("¿Que es java?");

        List lista = new ArrayList<AnswerDTO>();
        lista.add(answerDTO);

        when(useCaseGet.apply(question.getId())).thenReturn(Mono.just(questionDTO));
        when(answerRepository.save(any())).thenReturn(Mono.just(answer));


        StepVerifier.create(useCaseAddAnswer.apply(answerDTO))
                .expectNextMatches(questionDTO1 -> {
                    assert questionDTO1.getId().equals("1");
                    assert questionDTO1.getUserId().equals("xxxx-xxxx");
                    assert questionDTO1.getCategory().equals("software");
                    assert questionDTO1.getQuestion().equals("¿Que es java?");
                    assert questionDTO1.getType().equals("tech");
                    assert questionDTO1.getAnswers().equals(lista);
                    return true;
                })
                .verifyComplete();

        verify(useCaseGet).apply(question.getId());
        verify(answerRepository).save(any());
    }
}