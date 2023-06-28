package com.projectpractice.dto;

import com.projectpractice.entity.OptionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticDto {
    private String id;
    private String name;
    private String type;
    private Integer answerCount;
    private List<OptionEntity> option;
}
