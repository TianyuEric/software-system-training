package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.QuestionEntity;
import com.projectpractice.mapper.QuestionMapper;
import com.projectpractice.service.QuestionService;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceDefault
        extends ServiceImpl<QuestionMapper, QuestionEntity>
        implements QuestionService {
}
