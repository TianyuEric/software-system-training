package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.QuestionEntity;
import com.projectpractice.mapper.QuestionMapper;
import com.projectpractice.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:08
 * @Description: QuestionServiceImpl
 * @Version: 1.0
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, QuestionEntity> implements QuestionService {
}
