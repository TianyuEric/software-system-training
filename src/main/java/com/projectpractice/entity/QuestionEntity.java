package com.projectpractice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.entity
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  20:45
 * @Description: 问题实体
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("question_info")
public class QuestionEntity {
    @TableId
    private String id;
    private String name;
    private String content;
    private Integer answerCount;
    private String type;
    private String questionnaireId;
    private String isMust;
    private String isLink;
}
