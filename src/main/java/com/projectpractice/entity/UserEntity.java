package com.projectpractice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @TableId
    private String id;
    @ExcelProperty("用户名")
    private String username;
    private String password;
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;
    @ExcelProperty("结束时间")
    private LocalDateTime stopTime;
    @TableField(fill = FieldFill.INSERT)
    private String status;
    @ExcelProperty("创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    @TableField(fill = FieldFill.INSERT)
    @ExcelProperty("创建时间")
    private LocalDateTime creationDate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String lastUpdatedBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastUpdateDate;

}
