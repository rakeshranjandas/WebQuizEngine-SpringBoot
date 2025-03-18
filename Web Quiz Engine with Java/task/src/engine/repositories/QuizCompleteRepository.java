package engine.repositories;

import engine.entities.AppUser;
import engine.entities.QuizComplete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompleteRepository extends CrudRepository<QuizComplete, Long>, PagingAndSortingRepository<QuizComplete, Long> {
    Page<QuizComplete> findByAppUserOrderByCompletedAtDesc(AppUser appUser, Pageable pageable);
}
