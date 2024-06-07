package za.co.dsignweb.studentmanager.model.api.response;

import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;

import java.util.Set;

public record StudentRes(String studentNo,
                         String firstName,
                         String lastName,
                         String email,
                         String cellphoneNo,
                         String dob,
                         int score,
                         double average) {}
