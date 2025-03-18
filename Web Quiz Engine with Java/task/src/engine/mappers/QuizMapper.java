package engine.mappers;

import engine.dtos.QuizDTO;
import engine.entities.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizDTO toDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

}
