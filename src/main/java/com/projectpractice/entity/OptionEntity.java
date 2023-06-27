package com.projectpractice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @CreateTime: 2023-06-12  20:55
 * @Description: Option
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("option_info")
public class OptionEntity {
    @TableId
    private String id;
    private String chooseTerm;
    private String questionId;
    private Integer score;
    private Integer personCount;
    private String linkQuestionId;

}
