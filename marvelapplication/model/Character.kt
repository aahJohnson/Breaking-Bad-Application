package com.example.marvelapplication.model

import com.squareup.moshi.Json

class Character ( // Länka variabler till Json
    //@Json(name="char_id")
    //val charId : Int,
    @Json(name="name")
    val name : String,
    /*@Json(name="nickname")
    val nickname : String, */
    @Json(name="img")
    val image : String
)