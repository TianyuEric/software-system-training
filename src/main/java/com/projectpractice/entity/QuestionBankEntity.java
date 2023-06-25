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
 * @CreateTime: 2023-06-23  21:57
 * @Description: QuestionBankEntity
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("question_bank")
public class QuestionBankEntity {
    @TableId
    private String id;
    private String name;
    private String type;
}
