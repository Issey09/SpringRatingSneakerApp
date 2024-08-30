package org.example.ratingsneakerapp.main;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserVoteRepository extends JpaRepository<UserVote, Long> {
    Optional<UserVote> findByUsernameAndSneakerId(String username, long sneakerId);
}