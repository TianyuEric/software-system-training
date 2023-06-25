package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.ChosenAnswerEntity;
import com.projectpractice.mapper.ChosenAnswerMapper;
import com.projectpractice.service.ChosenAnswerService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-21  23:52
 * @Description: ChosenAnswerServiceImpl
 * @Version: 1.0
 */
@Service
public class ChosenAnswerServiceImpl extends ServiceImpl<ChosenAnswerMapper, ChosenAnswerEntity> implements ChosenAnswerService {
}
