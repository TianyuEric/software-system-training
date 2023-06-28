package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.Question;
import com.projectpractice.mapper.QuestionMapper;
import com.projectpractice.service.QuestionService;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
}
