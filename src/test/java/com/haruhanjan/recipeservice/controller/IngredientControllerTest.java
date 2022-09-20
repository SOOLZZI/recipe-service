package com.haruhanjan.recipeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.recipeservice.apiDocs.RestDocsConfiguration;
import com.haruhanjan.recipeservice.dto.ingredient.IngredientRequestDTO;
import com.haruhanjan.recipeservice.dto.ingredient.IngredientResponseDTO;
import com.haruhanjan.recipeservice.sample.IngredientSample;
import com.haruhanjan.recipeservice.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(IngredientController.class)
@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs
class IngredientControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientService ingredientService;

    IngredientSample ingredientSample = new IngredientSample();

    @Test
    @DisplayName("재료 전체 조회")
    void readAllTest() throws Exception {
        //given
        List<IngredientResponseDTO> list = new ArrayList<>();

        list.add(ingredientSample.getIngredientResponseDTO());
        list.add(ingredientSample.getIngredientResponseDTO());
        list.add(ingredientSample.getIngredientResponseDTO());
        list.add(ingredientSample.getIngredientResponseDTO());
        list.add(ingredientSample.getIngredientResponseDTO());
        list.add(ingredientSample.getIngredientResponseDTO());

        given(ingredientService.findAll()).willReturn(list);

        //when
        ResultActions result = mockMvc.perform(get("/api/ingredients")
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("get-ingredient-list",
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("재료 ID"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("재료 이름"),
                                fieldWithPath("[].type").type(JsonFieldType.STRING).description("재료 타입")
                        )
                ));

    }

    @Test
    @DisplayName("재료 생성")
    void createTest() throws Exception {
        //given
        IngredientRequestDTO dto = ingredientSample.getIngredientRequestDTO();
        //when
        ResultActions result = mockMvc.perform(post("/api/ingredients")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isCreated())
                .andDo(document("create-ingredient",
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("재료명"),
                                        fieldWithPath("ingredientType").type(JsonFieldType.STRING).description("재료 타입")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("생성된 id")
                                )
                        )
                );
    }
    
    @Test
    @DisplayName("재료 수정")
    void putTest() throws Exception {
        //given
        IngredientRequestDTO dto = ingredientSample.getIngredientRequestDTO();
        //when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/api/ingredients/{id}", 1)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isOk())
                .andDo(document("update-ingredient",
                        pathParameters(
                                parameterWithName("id").description("수정할 재료 아이디")
                        )
                        , requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("재료명"),
                                fieldWithPath("ingredientType").type(JsonFieldType.STRING).description("재료 타입")
                        )
                ));
    
    }

    @Test
    @DisplayName("재료 삭제")
    void deleteTest() throws Exception {
        //given

        //when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/ingredients/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));
        //then
        result.andExpect(status().isNoContent())
                .andDo(document("delete-ingredient",
                                pathParameters(parameterWithName("id").description("삭제할 재료 아이디"))
                        )
                );

    }
}