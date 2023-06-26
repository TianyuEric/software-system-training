package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.OptionEntity;
import com.projectpractice.mapper.OptionMapper;
import com.projectpractice.service.OptionService;
import org.springframework.stereotype.Service;


@Service
public class OptionServiceDefault
        extends ServiceImpl<OptionMapper, OptionEntity>
        implements OptionService {
}
