package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.OptionEntity;
import com.projectpractice.mapper.OptionMapper;
import com.projectpractice.service.OptionService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:09
 * @Description: OptionServiceImpl
 * @Version: 1.0
 */
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, OptionEntity> implements OptionService {
}
