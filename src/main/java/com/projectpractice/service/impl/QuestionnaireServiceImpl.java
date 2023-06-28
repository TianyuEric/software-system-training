package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.QuestionnaireEntity;
import com.projectpractice.mapper.QuestionnaireMapper;
import com.projectpractice.service.QuestionnaireService;
import org.springframework.stereotype.Service;


@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, QuestionnaireEntity> implements QuestionnaireService {
}
