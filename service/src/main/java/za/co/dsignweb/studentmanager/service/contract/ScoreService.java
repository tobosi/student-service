package za.co.dsignweb.studentmanager.service.contract;

import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;
import za.co.dsignweb.studentmanager.model.dto.ScoreDto;
import za.co.dsignweb.studentmanager.service.contract.crud.ICreate;

public interface ScoreService extends ICreate<ScoreReq, ScoreDto> {
}
