package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.UserEntity;
import com.projectpractice.mapper.UserMapper;
import com.projectpractice.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-03  19:00
 * @Description: UserServiceImpl
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
