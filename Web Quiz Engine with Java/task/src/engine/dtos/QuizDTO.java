package engine.dtos;

import java.util.List;

public class QuizDTO {

    private long id;
    private String title;
    private String text;
    private List<String> options;

    public QuizDTO(long id, String title, String text, List<String> options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }
}
