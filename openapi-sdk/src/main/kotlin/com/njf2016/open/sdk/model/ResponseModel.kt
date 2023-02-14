package com.njf2016.open.sdk.model

data class ResponseModel<T>(
    var content: T?,
    var status: Int = 200,
    var message: String = "Ok",
)