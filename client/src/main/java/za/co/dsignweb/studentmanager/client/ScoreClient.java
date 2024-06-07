package za.co.dsignweb.studentmanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import za.co.dsignweb.studentmanager.api.IRestCreate;
import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;
import za.co.dsignweb.studentmanager.model.api.response.ScoreRes;

@FeignClient(name = "score", url = "${student-service-base-url}/scores")
public interface ScoreClient extends IRestCreate<ScoreRes, ScoreReq> {
}
