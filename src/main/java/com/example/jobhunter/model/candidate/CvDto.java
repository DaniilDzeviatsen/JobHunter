package com.example.jobhunter.model.candidate;

import lombok.Value;

@Value
public class CvDto {
    String content;
    public static CvDto from(Cv cv){
        return new CvDto(cv.getContent());
    }
}
