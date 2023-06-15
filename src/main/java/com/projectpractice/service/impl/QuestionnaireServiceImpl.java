package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.QuestionnaireEntity;
import com.projectpractice.mapper.QuestionnaireMapper;
import com.projectpractice.service.QuestionnaireService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:07
 * @Description: QuestionnaireServiceImpl
 * @Version: 1.0
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, QuestionnaireEntity> implements QuestionnaireService {
}
