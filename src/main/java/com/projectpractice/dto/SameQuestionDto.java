package com.projectpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.dto
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-27  10:02
 * @Description: SameQuestionDto
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SameQuestionDto {
    private String questionnaireName;
    private String questionId;
    private Integer count;
}
