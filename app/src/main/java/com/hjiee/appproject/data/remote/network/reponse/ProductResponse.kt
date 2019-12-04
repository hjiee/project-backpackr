package com.hjiee.appproject.data.remote.network

data class ProductResponse(
    val statusCode : Int,
    val body : List<Body>
)

data class Body(
    val id : Int,                           // 상품코드
    val discount_cost : String,             // 상품 할인가
    val thumbnail_720 : String,             // 썸네일 이미지 (720 size)
    val thumbnail_list_320 : String,        // 썸네일 이미지 (320 size)
    val thumbnail_520 : String,             // 썸네일 이미지 (520 size)
    val title : String,                     // 게시글 제목
    val seller : String,                    // 게시글 작성자
    val description : String,               // 상품 설명
    val cost : String,                      // 상품 원가
    val discount_rate : String              // 상품 할인율
)