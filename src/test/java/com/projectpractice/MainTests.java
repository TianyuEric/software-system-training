package com.projectpractice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-08  19:15
 * @Description: 测试
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
class MainTests {
    @Test
    void testMain(){
        String[] args = new String[0];
        log.info(Arrays.toString(args));
        ProjectPracticeApplication.main(args);
    }

}
