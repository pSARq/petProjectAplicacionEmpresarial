package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecases.interfaces.SaveQuestion;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseCreate implements SaveQuestion {

    private final QuestionRepository repository;
    private final MapperUtil mapper;

    public UseCaseCreate(QuestionRepository repository, MapperUtil mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(QuestionDTO questionDTO) {
        return repository
                .save(mapper.mapperToQuestion(null).apply(questionDTO))
                .map(Question::getId);
    }
}
