package co.com.sofka.questions.repositories;


import co.com.sofka.questions.collections.Voto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VotoRepository extends ReactiveCrudRepository<Voto, String> {
    Mono<Voto> findByQuestionIdAndAnswerIdAndUserId(String questionId, String answerId, String userId);
}
