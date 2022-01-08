package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class QuestionDTO {

    private String id;
    @NotBlank(message = "El userId no puede ser nulo")
    private String userId;
    @NotBlank(message = "question no puede ser nula")
    private String question;
    @NotBlank(message = "type no puede ser nulo")
    private String type;
    @NotBlank(message = "category no puede ser nulo")
    private String category;
    private List<AnswerDTO> answers;

    public QuestionDTO(){

    }

    public QuestionDTO(@NotBlank String id, @NotBlank String userId, @NotBlank String question,
                       @NotBlank String type, @NotBlank String category) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
    }

    public QuestionDTO(@NotBlank String userId, @NotBlank String question, @NotBlank String type, @NotBlank String category) {
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<AnswerDTO> getAnswers() {
        this.answers = Optional.ofNullable(answers).orElse(new ArrayList<>());
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answer) {
        this.answers = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
