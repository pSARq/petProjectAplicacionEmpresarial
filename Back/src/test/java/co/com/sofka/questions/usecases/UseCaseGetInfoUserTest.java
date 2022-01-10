package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.mapper.MapperUtil;
import co.com.sofka.questions.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class UseCaseGetInfoUserTest {

    UserRepository userRepository;
    UseCaseGetInfoUser useCaseGetInfoUser;

    @BeforeEach
    public void setup(){
        MapperUtil mapperUtils = new MapperUtil();
        userRepository = mock(UserRepository.class);
        useCaseGetInfoUser = new UseCaseGetInfoUser(userRepository, mapperUtils);
    }

    @Test
    void getInfoUser() {
        User user = new User();
        user.setId("1");
        user.setUserId("userId");
        user.setName("nombre");
        user.setLastName("apellido");

        when(userRepository.findByUserId("userId")).thenReturn(Mono.just(user));

        StepVerifier.create(useCaseGetInfoUser.apply("userId"))
                .expectNextMatches(userDTO -> {
                    assert userDTO.getId().equals(user.getId());
                    assert userDTO.getUserId().equals(user.getUserId());
                    assert userDTO.getName().equals(user.getName());
                    assert userDTO.getLastName().equals(user.getLastName());
                    return true;
                })
                .verifyComplete();

        verify(userRepository).findByUserId("userId");
    }
}