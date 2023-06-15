package com.projectpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectpractice.entity.OptionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.mapper
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:04
 * @Description: OptionMapper
 * @Version: 1.0
 */
@Mapper
public interface OptionMapper extends BaseMapper<OptionEntity> {
}
