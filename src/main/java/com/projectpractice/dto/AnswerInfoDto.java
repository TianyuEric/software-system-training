package com.projectpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.dto
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-23  14:40
 * @Description: AnswerInfoDto
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerInfoDto {
    private String id;
    private String questionnaireName;
    private String questionnaireId;
    private String username;
    private LocalDateTime answerTime;
}
