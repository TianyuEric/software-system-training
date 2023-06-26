package com.projectpractice.entity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("answer_info")
public class AnswerInfo {
    @TableId
    private String id;
    private String questionnaireId;
    private String roleId;
    private LocalDateTime answerTime;
}
