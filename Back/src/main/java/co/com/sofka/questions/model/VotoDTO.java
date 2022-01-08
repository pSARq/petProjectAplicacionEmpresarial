package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class VotoDTO{

    private String id;
    @NotBlank(message = "El userId no puede ser nulo")
    private String userId;
    @NotBlank(message = "questionId no puede ser nula")
    private String questionId;
    @NotBlank(message = "answerId no puede ser nula")
    private String answerId;
    @NotNull(message = "voto no puede ser nula")
    private int voto;


    public VotoDTO() {
    }

    public VotoDTO(String id, @NotBlank String userId, @NotBlank String questionId, @NotBlank String answerId, @NotNull int voto) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.voto = voto;
    }

    public VotoDTO(@NotBlank String userId, @NotBlank String questionId, @NotBlank String answerId, @NotBlank int voto) {
        this.userId = userId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.voto = voto;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotoDTO votoDTO = (VotoDTO) o;
        return Objects.equals(id, votoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VotoDTO{" +
                "userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", voto=" + voto +
                '}';
    }
}
