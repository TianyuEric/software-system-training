package com.projectpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.dto
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-23  17:12
 * @Description: ChosenAnswerDto
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChosenAnswerDto {
    private String questionnaireId;
    private String roleId;
    private List<Map<String, String >> answer;
}
