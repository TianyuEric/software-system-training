package com.projectpractice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.entity
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:00
 * @Description: Answer
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("answer_info")
public class AnswerEntity {
    @TableId
    private String id;
    private String questionnaireId;
    private String roleId;
    private LocalDateTime answerTime;
}
