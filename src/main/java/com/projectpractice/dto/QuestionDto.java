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
 * @CreateTime: 2023-06-21  21:30
 * @Description: QuestionDto
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private String id;
    private String name;
    private String isMust;
    private String type;
    private List<OptionEntity> option;
}
