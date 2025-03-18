package engine.entities;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String text;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private AppUser createdBy;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizComplete> quizComplete;

    public Quiz() {
        title = "";
        text = "";
        options = List.of();
        answer = List.of();
        createdBy = null;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        if (answer == null) return;
        this.answer = answer;
        Collections.sort(this.answer);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public AppUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser appUser) {
        createdBy = appUser;
    }

    public List<QuizComplete> getQuizComplete() {
        return quizComplete;
    }

    public void setQuizComplete(List<QuizComplete> quizComplete) {
        this.quizComplete = quizComplete;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", createdBy=" + createdBy +
                '}';
    }
}
