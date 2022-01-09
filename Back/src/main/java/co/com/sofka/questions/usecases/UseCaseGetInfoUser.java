package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.repositories.UserRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseGetInfoUser implements Function<String, Mono<UserDTO>> {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UseCaseGetInfoUser(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    public Mono<UserDTO> apply(String userId) {
        return userRepository.findByUserId(userId)
                .map(mapperUtil.mapEntityToUser());
    }
}
