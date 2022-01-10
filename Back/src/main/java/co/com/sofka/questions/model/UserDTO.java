package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDTO {

    private String id;
    @NotBlank(message = "El userId no puede ser nulo")
    private String userId;
    private String name;
    private String lastName;
    @NotBlank(message = "El email no puede ser nulo")

    public UserDTO() {
    }

    public UserDTO(String id, @NotBlank String userId, String name, String lastName) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
    }

    public UserDTO(@NotBlank String userId, String name, String lastName) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
