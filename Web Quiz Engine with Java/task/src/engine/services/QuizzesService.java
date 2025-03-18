package engine.services;

import engine.dtos.QuizCompleteDTO;
import engine.dtos.QuizDTO;
import engine.entities.AppUser;
import engine.entities.Quiz;
import engine.entities.QuizComplete;
import engine.exceptions.DeleteUnauthorizedException;
import engine.exceptions.QuizNotFoundException;
import engine.mappers.QuizMapper;
import engine.repositories.QuizCompleteRepository;
import engine.repositories.QuizRepository;
import engine.requests.CreateQuizRequest;
import engine.requests.SolveQuizRequest;
import engine.responses.AnswerResponse;
import engine.responses.BadAnswerResponse;
import engine.responses.GoodAnswerResponse;
import engine.security.AppUserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizzesService {

    @Autowired
    private QuizMapper quizMapper;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizCompleteRepository quizCompleteRepository;

    private final int PAGE_SIZE = 10;

    private AppUser getCurrentAppUser() {
        AppUserAdapter appUserAdapter = (AppUserAdapter) SecurityContextHolder
                                            .getContext()
                                            .getAuthentication()
                                            .getPrincipal();

        return appUserAdapter.getAppUser();
    }

    public QuizDTO createQuiz(CreateQuizRequest createQuizRequest) {
        Quiz quiz = new Quiz();
        quiz.setTitle(createQuizRequest.getTitle());
        quiz.setText(createQuizRequest.getText());
        quiz.setOptions(createQuizRequest.getOptions());
        quiz.setAnswer(createQuizRequest.getAnswer());
        quiz.setCreatedBy(getCurrentAppUser());

        Quiz createdQuiz = quizRepository.save(quiz);
        return quizMapper.toDTO(createdQuiz);
    }

    public QuizDTO getQuizById(long id) {
        Optional<Quiz> quizById = quizRepository.findById(id);

        if (quizById.isEmpty()) {
            throw new QuizNotFoundException();
        }

        return quizMapper.toDTO(quizById.get());
    }

    public Page<QuizDTO> getAll(int page) {
        Page<Quiz> allQuizzes =  quizRepository.findAll(PageRequest.of(page, PAGE_SIZE));
        Page<QuizDTO> allQuizzesDTO = allQuizzes.map((quiz) -> quizMapper.toDTO(quiz));

        return allQuizzesDTO;
    }

    public AnswerResponse solveQuiz(long id, SolveQuizRequest solveQuizRequest) {

        List solveAnswers = solveQuizRequest.getAnswer();
        Collections.sort(solveAnswers);

        Optional<Quiz> quizById = quizRepository.findById(id);

        if (quizById.isEmpty()) {
            throw new QuizNotFoundException();
        }

        Quiz quiz = quizById.get();

        if ( ! solveAnswers.equals(quiz.getAnswer())) {
            return new BadAnswerResponse();
        }

        QuizComplete quizComplete = new QuizComplete();
        quizComplete.setQuiz(quiz);
        quizComplete.setAppUser(getCurrentAppUser());

        quizCompleteRepository.save(quizComplete);

        return new GoodAnswerResponse();
    }

    public void deleteQuiz(long id) {
        Optional<Quiz> quizById = quizRepository.findById(id);

        if (quizById.isEmpty()) {
            throw new QuizNotFoundException();
        }

        if (getCurrentAppUser().getId() != quizById.get().getCreatedBy().getId()) {
            throw new DeleteUnauthorizedException();
        }

        quizRepository.deleteById(id);
    }

    public Page<QuizCompleteDTO> getCompleted(int page) {

        Page<QuizComplete> quizCompletePage = quizCompleteRepository
                    .findByAppUserOrderByCompletedAtDesc(
                            getCurrentAppUser(),
                            PageRequest.of(page, PAGE_SIZE)
                    );

        Page<QuizCompleteDTO> quizCompleteDTOPage = quizCompletePage
                    .map(
                            (quizComplete) -> new QuizCompleteDTO(
                                                quizComplete.getQuiz().getId(),
                                                quizComplete.getCompletedAt()
                            )
                    );

        return quizCompleteDTOPage;
    }
}
