package engine.responses;

public class GoodAnswerResponse extends AnswerResponse {
    public GoodAnswerResponse() {
        this.setSuccess(true);
        this.setFeedback("Congratulations, you're right!");
    }
}
