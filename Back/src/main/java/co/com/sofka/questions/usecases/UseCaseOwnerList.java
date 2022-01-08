package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class UseCaseOwnerList implements Function<String, Flux<QuestionDTO>> {

    private final QuestionRepository questionRepository;
    private final MapperUtil mapper;

    public UseCaseOwnerList(QuestionRepository questionRepository, MapperUtil mapper) {
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    @Override
    public Flux<QuestionDTO> apply(String userId) {
        return questionRepository.findByUserId(userId)
                .map(mapper.mapEntityToQuestion());
    }
}
