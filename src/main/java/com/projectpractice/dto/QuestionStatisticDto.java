package com.projectpractice.dto;

import com.projectpractice.entity.OptionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.dto
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-26  10:25
 * @Description: QuestionStatisticDto
 * @Version: 1.0
 */
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
