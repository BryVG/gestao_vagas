package br.bryan.gestao_vagas.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.bryan.gestao_vagas.modules.company.dto.CreateJobDTO;

public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test 
    public void shoud_be_able_to_create_a_new_job() {
        var createdJobDTO = CreateJobDTO.builder()
                .benefits("Benefitstest")
                .description("descriptiontest")
                .level("leveltest")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(createdJobDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}