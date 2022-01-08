package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class UseCaseGet implements Function<String, Mono<QuestionDTO>> {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MapperUtil mapper;

    public UseCaseGet(QuestionRepository questionRepository, AnswerRepository answerRepository, MapperUtil mapper){
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<QuestionDTO> apply(String id) {
        Objects.requireNonNull(id, "El id no puede ser nulo");
        return questionRepository.findById(id)
                .map(mapper.mapEntityToQuestion())
                .flatMap(mapQuestionAggregate());
    }

    private Function<QuestionDTO, Mono<QuestionDTO>> mapQuestionAggregate() {
        return questionDTO ->
                Mono.just(questionDTO).zipWith(
                        answerRepository.findAllByQuestionId(questionDTO.getId())
                                .map(mapper.mapEntityToAnswer())
                                //Ordena de mayor a menor
                                .sort((question, question2) -> question2.getPosition() - question.getPosition())
                                .collectList(),
                        (question, answers) ->{
                            question.setAnswers(answers);
                            return question;
                        }
                );
    }
}
