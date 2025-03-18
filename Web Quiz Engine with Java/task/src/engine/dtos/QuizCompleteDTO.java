package engine.dtos;

import java.time.LocalDateTime;

public class QuizCompleteDTO {

    private long id;

    private LocalDateTime completedAt;

    public QuizCompleteDTO(long id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
