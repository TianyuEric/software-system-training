package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.QuestionBank;
import com.projectpractice.mapper.QuestionBankMapper;
import com.projectpractice.service.QuestionRepoService;
import org.springframework.stereotype.Service;


@Service
public class QuestionRepoServiceDefault
        extends ServiceImpl<QuestionBankMapper, QuestionBank>
        implements QuestionRepoService {
}
