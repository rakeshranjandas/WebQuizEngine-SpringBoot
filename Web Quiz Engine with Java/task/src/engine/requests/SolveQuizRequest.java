package engine.requests;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class SolveQuizRequest {
    @NotNull
    private List<Integer> answer;

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SolveQuizRequest{" +
                "answer=" + answer +
                '}';
    }
}
