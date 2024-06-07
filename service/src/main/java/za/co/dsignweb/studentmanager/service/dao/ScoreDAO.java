package za.co.dsignweb.studentmanager.service.dao;

import org.springframework.stereotype.Component;
import za.co.dsignweb.studentmanager.db.entity.Score;
import za.co.dsignweb.studentmanager.db.repository.ScoreRepository;

@Component
public class ScoreDAO {

    private final ScoreRepository scoreRepository;

    public ScoreDAO(final ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score save(final Score score) {
        return scoreRepository.save(score);
    }
}
