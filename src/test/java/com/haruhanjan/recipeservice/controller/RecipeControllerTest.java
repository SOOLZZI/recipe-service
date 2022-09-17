package com.haruhanjan.recipeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.recipeservice.apiDocs.RestDocsConfiguration;
import com.haruhanjan.recipeservice.dto.recipe.RecipeResponseDTO;
import com.haruhanjan.recipeservice.sample.RecipeSample;
import com.haruhanjan.recipeservice.service.RecipeService;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs
class RecipeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    RecipeSample recipeSample = new RecipeSample();


    @Test
    @DisplayName("레시피 전체 조회")
    void readAll() throws Exception {
        //given

        List<RecipeResponseDTO> list = new ArrayList<>();
        list.add(recipeSample.getRecipeResponseDTO());
        list.add(recipeSample.getRecipeResponseDTO());
        list.add(recipeSample.getRecipeResponseDTO());
        list.add(recipeSample.getRecipeResponseDTO());

        given(recipeService.findAll()).willReturn(list);

        //when
        ResultActions result = mockMvc.perform(
                get("/api/recipes")
                        .content(objectMapper.writeValueAsString(list))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("get-recipe-list",
                        requestFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("레시피 ID"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("레시피 이름"),
                                fieldWithPath("[].description").type(JsonFieldType.STRING).description("레시피 설명"),
                                fieldWithPath("[].writer").type(JsonFieldType.STRING).description("작성자"),
                                fieldWithPath("[].ingredients.[].id").type(JsonFieldType.NUMBER).description("재료 ID"),
                                fieldWithPath("[].ingredients.[].name").type(JsonFieldType.STRING).description("재료명"),
                                fieldWithPath("[].processes.[].cookingOrder").type(JsonFieldType.NUMBER).description("조리 순서"),
                                fieldWithPath("[].processes.[].description").type(JsonFieldType.STRING).description("조리방법"),
                                fieldWithPath("[].cookingTime").type(JsonFieldType.STRING).description("총 요리 시간")
                        )
                ));
    }
}



