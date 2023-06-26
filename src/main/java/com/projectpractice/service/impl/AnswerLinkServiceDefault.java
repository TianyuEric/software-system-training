package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.AnswerLink;
import com.projectpractice.mapper.AnswerLinkMapper;
import com.projectpractice.service.AnswerLinkService;
import org.springframework.stereotype.Service;


@Service
public class AnswerLinkServiceDefault
        extends ServiceImpl<AnswerLinkMapper, AnswerLink>
        implements AnswerLinkService { }
