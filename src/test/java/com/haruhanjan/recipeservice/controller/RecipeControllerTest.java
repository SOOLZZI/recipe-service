package com.haruhanjan.recipeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.recipeservice.apiDocs.RestDocsConfiguration;
import com.haruhanjan.recipeservice.dto.recipe.RecipeRequestDTO;
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
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("????????? ?????? ??????")
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
                        //.content(objectMapper.writeValueAsString(list))
                        // .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("get-recipe-list",
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("????????? ID"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("????????? ??????"),
                                fieldWithPath("[].description").type(JsonFieldType.STRING).description("????????? ??????"),
                                fieldWithPath("[].writer").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("[].ingredients.[].id").type(JsonFieldType.NUMBER).description("?????? ID"),
                                fieldWithPath("[].ingredients.[].name").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("[].processes.[].cookingOrder").type(JsonFieldType.NUMBER).description("?????? ??????"),
                                fieldWithPath("[].processes.[].description").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("[].cookingTime").type(JsonFieldType.STRING).description("??? ?????? ??????")
                        )
                ));
    }

    @Test
    @DisplayName("????????? ??????")
    void createRecipe() throws Exception {
        //given
        RecipeRequestDTO dto = recipeSample.getRecipeRequestDTO();

        given(recipeService.save(any())).willReturn(1000L);

        //when
        ResultActions result = mockMvc.perform(post("/api/recipes")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isCreated())
                .andDo(document("create-recipe",
                                requestFields(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("????????? ??????"),
                                        fieldWithPath("description").type(JsonFieldType.STRING).description("????????? ??????"),
                                        fieldWithPath("writer").type(JsonFieldType.STRING).description("????????? ?????????"),
                                        fieldWithPath("ingredients").type(JsonFieldType.ARRAY).description("????????? ?????? id"),
                                        fieldWithPath("processes").type(JsonFieldType.ARRAY).description("????????? ????????????"),
                                        fieldWithPath("cookingTime").type(JsonFieldType.STRING).description("????????? ??? ????????????")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? id")
                                )
                        )
                );

    }

    @Test
    @DisplayName("????????? ??????")
    void putRecipe() throws Exception {
        //given
        RecipeRequestDTO dto = recipeSample.getRecipeRequestDTO();

        //when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/api/recipes/{id}", 1)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("update-recipe",
                        pathParameters(
                                parameterWithName("id").description("????????? ????????? ?????????")
                        )
                        , requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("????????? ??????"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("????????? ??????"),
                                fieldWithPath("writer").type(JsonFieldType.STRING).description("????????? ?????????"),
                                fieldWithPath("ingredients").type(JsonFieldType.ARRAY).description("????????? ?????? id"),
                                fieldWithPath("processes").type(JsonFieldType.ARRAY).description("????????? ????????????"),
                                fieldWithPath("cookingTime").type(JsonFieldType.STRING).description("????????? ??? ????????????")
                        ))
                );
    }

    @Test
    @DisplayName("????????? ??????")
    void deleteRecipe() throws Exception {
        //given

        //when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/recipes/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isNoContent())
                .andDo(document("delete-recipe",
                                pathParameters(parameterWithName("id").description("????????? ????????? ?????????"))
                        )
                );
    }

}



