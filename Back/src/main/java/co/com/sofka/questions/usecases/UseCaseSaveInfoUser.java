package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.collections.Voto;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.repositories.UserRepository;
import co.com.sofka.questions.usecases.interfaces.SaveUser;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UseCaseSaveInfoUser implements SaveUser {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UseCaseSaveInfoUser(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public Mono<String> apply(UserDTO userDTO) {
        Objects.requireNonNull(userDTO);
        return userRepository.save(mapperUtil.mapperToUser().apply(userDTO))
                .map(User::getId);
    }

}
