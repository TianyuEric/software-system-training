package com.projectpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectpractice.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.mapper
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-03  18:54
 * @Description: userMapper
 * @Version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
