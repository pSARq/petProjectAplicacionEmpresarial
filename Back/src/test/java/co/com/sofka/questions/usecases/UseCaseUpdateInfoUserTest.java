package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UseCaseUpdateInfoUserTest {

    UserRepository userRepository;
    UseCaseUpdateInfoUser useCaseUpdateInfoUser;

    @BeforeEach
    public void setup(){
        MapperUtil mapperUtils = new MapperUtil();
        userRepository= mock(UserRepository.class);
        useCaseUpdateInfoUser = new UseCaseUpdateInfoUser(userRepository, mapperUtils);
    }

    @Test
    void updateInfoUser() {
        UserDTO dto = new UserDTO();
        dto.setId("1");
        dto.setUserId("userId");
        dto.setName("nombre");
        dto.setLastName("apellido");

        User user = new User();
        user.setId("1");
        user.setUserId("userId");
        user.setName("nombre");
        user.setLastName("apellido");

        when(userRepository.save(any())).thenReturn(Mono.just(user));

        StepVerifier.create(useCaseUpdateInfoUser.apply(dto))
                .expectNextMatches(id -> {
                    assert id.equals("1");
                    return true;
                })
                .verifyComplete();

        verify(userRepository).save(any());
    }
}