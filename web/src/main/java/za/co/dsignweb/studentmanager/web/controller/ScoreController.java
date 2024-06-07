package za.co.dsignweb.studentmanager.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.dsignweb.studentmanager.api.IRestCreate;
import za.co.dsignweb.studentmanager.api.RestConverter;
import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;
import za.co.dsignweb.studentmanager.model.api.response.ScoreRes;
import za.co.dsignweb.studentmanager.model.dto.ScoreDto;
import za.co.dsignweb.studentmanager.service.contract.ScoreService;

@RestController
@RequestMapping("/scores")
public class ScoreController implements IRestCreate<ScoreRes, ScoreReq>, RestConverter<ScoreRes, ScoreDto> {

    private final ScoreService scoreService;
    private final ObjectMapper mapper;

    public ScoreController(final ScoreService scoreService,
                           final ObjectMapper mapper) {
        this.scoreService = scoreService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ScoreRes> create(final ScoreReq request) {
        return convert(mapper, scoreService.create(request), ScoreRes.class);
    }
}
