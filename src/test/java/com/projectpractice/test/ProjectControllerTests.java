package com.projectpractice.test;

import com.alibaba.fastjson.JSON;
import com.projectpractice.ProjectPracticeApplication;
import com.projectpractice.dto.ChosenAnswerDto;
import com.projectpractice.entity.AnswerEntity;
import com.projectpractice.entity.ProjectEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectPracticeApplication.class)
@Slf4j

public class ProjectControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        // 设定mockMvc能mock的controller是整个springBoot项目环境中的controller
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    void testList(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("pageNum",1);
//        map.put("pageSize",15);
//        map.put("username","admin");
//        map.put("projectId","1668226706322665474");
//        String body = JSON.toJSONString(map);
//        MvcResult mvcResult;
//        try {
//            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/answer/list")
//                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
//                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    // 正式执行接口,并返回接口的返回值
//                    .andReturn();
//            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//            log.info(mvcResult.getResponse().getContentAsString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    void testListNull(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("pageNum",5);
//        map.put("pageSize",10);
//        map.put("username","admin");
//        map.put("projectId","1668226706322665474");
//        String body = JSON.toJSONString(map);
//        MvcResult mvcResult;
//        try {
//            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/answer/list")
//                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
//                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    // 正式执行接口,并返回接口的返回值
//                    .andReturn();
//            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//            log.info(mvcResult.getResponse().getContentAsString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    void testSubmit(){
////        ChosenAnswerDto chosenAnswerDto = new ChosenAnswerDto();
////        chosenAnswerDto.setQuestionnaireId("1672162070196310018");
////        chosenAnswerDto.setRoleId("1668209171955138565");
////
////        List<Map<String, String>> mapList = new ArrayList<>();
////        Map<String,String> map = new HashMap<>();
////        map.put("1672162118766350338","1672191013045583874");
////        mapList.add(map);
////
////        chosenAnswerDto.setAnswer(mapList);
//
//        String body = "{\n" +
//                "    \"roleId\": \"1668209171955138565\",\n" +
//                "    \"questionnaireId\": \"1673285684975054850\",\n" +
//                "    \"answer\": [\n" +
//                "        {\n" +
//                "            \"questionId\": \"1673285707301335041\",\n" +
//                "            \"optionId\": \"1673285712967839747\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"questionId\": \"1673285727308165121\",\n" +
//                "            \"optionId\": \"1673285880022773762\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"questionId\": \"1673285816885915650\",\n" +
//                "            \"optionId\": \"1673285880022773762\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"questionId\": \"1673285707494273025\",\n" +
//                "            \"optionId\": \"1673285719213158402\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        MvcResult mvcResult;
//        try {
//            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/answer/submit")
//                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
//                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    // 正式执行接口,并返回接口的返回值
//                    .andReturn();
//            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//            log.info(mvcResult.getResponse().getContentAsString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    void testSubmitEmpty(){
//        ChosenAnswerDto chosenAnswerDto = new ChosenAnswerDto();
//        chosenAnswerDto.setQuestionnaireId("1673285684975054850");
//        chosenAnswerDto.setRoleId("1668209171955138565");
//        List<Map<String, String>> mapList = new ArrayList<>();
//        Map<String,String> map = new HashMap<>();
//        map.put("1673285707301335041","1673285712967839747");
//        mapList.add(map);
//        chosenAnswerDto.setAnswer(mapList);
//
//        String body = JSON.toJSONString(mapList);
//        MvcResult mvcResult;
//        try {
//            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/answer/submit")
//                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
//                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    // 正式执行接口,并返回接口的返回值
//                    .andReturn();
//            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//            log.info(mvcResult.getResponse().getContentAsString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Test
    void testQueryProjectList(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322665474");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/queryProjectList")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testQueryProjectListWithEmpty1(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(null);
        projectEntity.setProjectName(null);

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/queryProjectList")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testQueryProjectListWithEmpty2(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322665474");
        projectEntity.setProjectName(null);

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/queryProjectList")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddProjectInfo(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322665474");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/addProjectInfo")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddProjectInfoWithEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322667474");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/addProjectInfo")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testModifyProjectInfo(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322665474");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/modifyProjectInfo")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testModifyProjectInfoWithEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322667474");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/modifyProjectInfo")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteProject(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322665474");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/deleteProjectById")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteProjectWithEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1668226706322667474");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/deleteProjectById")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
