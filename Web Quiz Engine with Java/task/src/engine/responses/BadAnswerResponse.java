package engine.responses;

public class BadAnswerResponse extends AnswerResponse {
    public BadAnswerResponse() {
        this.setSuccess(false);
        this.setFeedback("Wrong answer! Please, try again.");
    }
}
