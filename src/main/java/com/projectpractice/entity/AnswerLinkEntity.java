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
 * @CreateTime: 2023-06-21  23:37
 * @Description: AnswerLinkEntity
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("answer_link")
public class AnswerLinkEntity {
    @TableId
    private String id;
    private String questionId;
    private String userId;
    private String answerId;

}
