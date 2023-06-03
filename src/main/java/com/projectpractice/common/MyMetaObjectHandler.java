package com.projectpractice.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.common
 * @Author: Tianyu Han
 * @CreateTime: 2022-07-26  15:43
 * @Description: 元数据对象处理器
 * @Version: 1.0
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "creationDate", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "createdBy", UserMessage::getUsername, String.class);
        this.strictInsertFill(metaObject, "status", String.class, "1");
        this.fillStrategy(metaObject, "lastUpdatedBy", UserMessage.getUsername());
        this.fillStrategy(metaObject, "lastUpdateDate", LocalDateTime.now());
    }

    //修改时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "lastUpdatedBy", UserMessage.getUsername());
        this.fillStrategy(metaObject, "lastUpdateDate", LocalDateTime.now());
    }
}
