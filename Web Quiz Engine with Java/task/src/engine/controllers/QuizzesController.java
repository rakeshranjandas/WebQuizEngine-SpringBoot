package engine.controllers;

import engine.dtos.QuizCompleteDTO;
import engine.dtos.QuizDTO;
import engine.entities.QuizComplete;
import engine.requests.CreateQuizRequest;
import engine.requests.SolveQuizRequest;
import engine.responses.AnswerResponse;
import engine.services.QuizzesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quizzes")
public class QuizzesController {

    @Autowired
    private QuizzesService quizzesService;

    @PostMapping
    public QuizDTO createQuiz(@Valid @RequestBody CreateQuizRequest createQuizRequest) {
        return quizzesService.createQuiz(createQuizRequest);
    }

    @GetMapping
    public Page<QuizDTO> getAllQuizzes(@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        return quizzesService.getAll(page);
    }

    @GetMapping("/{id}")
    public QuizDTO getQuiz(@PathVariable("id") long id) {
        return quizzesService.getQuizById(id);
    }

    @PostMapping("/{id}/solve")
    public AnswerResponse solveQuiz(@PathVariable("id") long id, @Valid @RequestBody SolveQuizRequest solveQuizRequest) {
        return quizzesService.solveQuiz(id, solveQuizRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuiz(@PathVariable("id") long id) {
        quizzesService.deleteQuiz(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/completed")
    public Page<QuizCompleteDTO> getCompletedQuizzes(@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        return quizzesService.getCompleted(page);
    }
}
