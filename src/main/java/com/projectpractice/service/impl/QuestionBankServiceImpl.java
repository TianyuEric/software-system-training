package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.QuestionBankEntity;
import com.projectpractice.mapper.QuestionBankMapper;
import com.projectpractice.service.QuestionBankService;
import org.springframework.stereotype.Service;


@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBankEntity> implements QuestionBankService {
}
