package com.example.demoTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    void testController() {
        assertThat(studentController).isNotNull();
    }
    private StudentEntity createStudent() throws Exception{
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1L);
        studentEntity.setName("Mario");
        studentEntity.setSurname("Rossi");
        studentEntity.setWorking(true);

        String studentJSon = objectMapper.writeValueAsString(studentEntity);

        MvcResult result = this.mockMvc.perform(post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJSon))
                .andExpect(status().isOk())
                .andReturn();
        String studentJSon1 = result.getResponse().getContentAsString();
        return objectMapper.readValue(studentJSon1, StudentEntity.class);
    }
    private StudentEntity getStudentFromId(Long id) throws Exception{
        MvcResult result = this.mockMvc.perform(get("/api/student/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        try{
            String studentJSon = result.getResponse().getContentAsString();
            return objectMapper.readValue(studentJSon, StudentEntity.class);
        }catch (Exception e){
            return null;
        }
    }
    @Test
    void createStudentTest() throws Exception{
        StudentEntity studentEntity = createStudent();
        assertThat(studentEntity.getId()).isNotNull();
    }
    @Test
    void getStudentByIdTest() throws Exception{
        StudentEntity studentEntity = createStudent();
        assertThat(studentEntity.getId()).isNotNull();
        StudentEntity studentResponse = getStudentFromId(studentEntity.getId());
        assertThat(studentResponse.getId()).isEqualTo(studentEntity.getId());
    }
    @Test
    void deleteStudentTest() throws Exception{
        StudentEntity studentEntity = createStudent();
        assertThat(studentEntity.getId()).isNotNull();

        this.mockMvc.perform(delete("/api/delete/" + studentEntity.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        StudentEntity studentFromResponseGet = getStudentFromId(studentEntity.getId());
        assertThat(studentFromResponseGet).isNull();
    }

    @Test
    void updateStudentStatusTest() throws Exception{
        StudentEntity studentEntity = createStudent();
        assertThat(studentEntity.getId()).isNotNull();

        MvcResult result = this.mockMvc.perform(put("/api/isworking/" + studentEntity.getId() + "?working=false" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        StudentEntity studentFromResponsePut = objectMapper.readValue(result.getResponse().getContentAsString(), StudentEntity.class);
        assertThat(studentFromResponsePut.getId()).isEqualTo(studentEntity.getId());
        assertThat(studentFromResponsePut.isWorking()).isEqualTo(false);
    }
}
