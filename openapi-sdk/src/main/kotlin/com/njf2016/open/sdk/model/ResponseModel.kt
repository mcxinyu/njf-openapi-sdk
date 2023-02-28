package com.njf2016.open.sdk.model

/**
 * 农卷风开放服务返回数据封装类
 *
 * @param T
 * @property content 接口有效数据
 * @property status 状态
 * @property message 简单消息
 * @constructor Create empty Response model
 */
data class ResponseModel<T>(
    var content: T?,
    var status: Int = 200,
    var message: String = "Ok",
)