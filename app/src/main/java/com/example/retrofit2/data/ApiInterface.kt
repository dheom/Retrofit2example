package com.example.retrofit2.data

import com.example.retrofit2.model.CatFacts
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
//http의 요청을 처리하는 곳
    @GET("/fact")//자료를 받는고 -> 엔드포인트를 설정해줘야함
    suspend fun getRandomFact(): Response<CatFacts>

}