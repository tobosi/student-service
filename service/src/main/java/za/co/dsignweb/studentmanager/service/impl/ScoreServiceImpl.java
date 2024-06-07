package za.co.dsignweb.studentmanager.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import za.co.dsignweb.studentmanager.db.entity.Score;
import za.co.dsignweb.studentmanager.db.entity.Student;
import za.co.dsignweb.studentmanager.db.repository.ScoreRepository;
import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;
import za.co.dsignweb.studentmanager.model.dto.ScoreDto;
import za.co.dsignweb.studentmanager.service.contract.ScoreService;
import za.co.dsignweb.studentmanager.service.dao.StudentDAO;

import java.util.UUID;

@Transactional
@Service
public class ScoreServiceImpl implements ScoreService {

    private ScoreRepository scoreRepository;
    private final StudentDAO studentDAO;

    public ScoreServiceImpl(final ScoreRepository scoreRepository,
                            final StudentDAO studentDAO) {
        this.scoreRepository = scoreRepository;
        this.studentDAO = studentDAO;
    }

    @Override
    public ScoreDto create(final ScoreReq request) {
        final Student student = studentDAO.find(request.studentNo());
        final Score score = build(request, student);
        score.setRefNo(UUID.randomUUID().toString());
        return scoreRepository.save(score).toDTO();
    }

    public static Score build(final ScoreReq request, final Student student) {
        final Score score = new Score(request.score(), student);
        score.setRefNo(UUID.randomUUID().toString());
        return score;
    }
}
