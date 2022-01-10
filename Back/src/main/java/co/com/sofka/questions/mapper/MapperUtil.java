package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.collections.Voto;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.model.VotoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtil {

    public Function<AnswerDTO, Answer> mapperToAnswer(){
        return updateAnswer -> {
            Answer answer = new Answer();
            answer.setId(updateAnswer.getId());
            answer.setUserId(updateAnswer.getUserId());
            answer.setQuestionId(updateAnswer.getQuestionId());
            answer.setAnswer(updateAnswer.getAnswer());
            answer.setPosition(updateAnswer.getPosition());
            return answer;
        };
    }

    public Function<Answer, AnswerDTO> mapEntityToAnswer(){
        return entity -> new AnswerDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestionId(),
                entity.getAnswer(),
                entity.getPosition()
        );
    }

    public Function<QuestionDTO, Question> mapperToQuestion(String id){
        return updateQuestion -> {
            Question question = new Question();
            question.setId(id);
            question.setUserId(updateQuestion.getUserId());
            question.setQuestion(updateQuestion.getQuestion());
            question.setType(updateQuestion.getType());
            question.setCategory(updateQuestion.getCategory());
            return question;
        };
    }

    public Function<Question, QuestionDTO> mapEntityToQuestion(){
        return entity -> new QuestionDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestion(),
                entity.getType(),
                entity.getCategory()
        );
    }

    public Function<VotoDTO, Voto> mapperToVoto(){
        return updateVoto -> {
            Voto voto = new Voto();
            voto.setId(updateVoto.getId());
            voto.setUserId(updateVoto.getUserId());
            voto.setQuestionId(updateVoto.getQuestionId());
            voto.setAnswerId(updateVoto.getAnswerId());
            voto.setVoto(updateVoto.getVoto());
            return voto;
        };
    }

    public Function<Voto, VotoDTO> mapEntityToVoto(){
        return entity -> new VotoDTO(
                entity.getUserId(),
                entity.getQuestionId(),
                entity.getAnswerId(),
                entity.getVoto()
        );
    }

    public Function<UserDTO, User> mapperToUser(){
        return updateUser -> {
            User user = new User();
            user.setId(updateUser.getId());
            user.setUserId(updateUser.getUserId());
            user.setName(updateUser.getName());
            user.setLastName(updateUser.getLastName());
            return user;
        };
    }

    public Function<User, UserDTO> mapEntityToUser(){
        return entity -> new UserDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getLastName()
        );
    }
}
