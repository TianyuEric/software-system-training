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
 * @CreateTime: 2023-06-21  23:38
 * @Description: ChosenAnswerEntity
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("chosen_answer")
public class ChosenAnswerEntity {
    @TableId
    private String id;
    private String linkId;
    private String optionId;
}
