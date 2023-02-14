package com.njf2016.open.sdk.model

import java.io.Serializable

data class Concentrator(
    val id: String,
    val concentrator: String,
    val createTime: String?,
    val updateTime: String?,
    val deviceType: String?,
    val lat: Double?,
    val lng: Double?,
    val name: String?,
    val online: Int?,
    val baud: Int?,
    val period: Int?,
    val address: String?,
    val sort: Int?,
) : Serializable

data class Device(
    val id: String,
    val createtime: String?,
    val valid: Int?,
    val concentrator: String,
    val circuit: String?,
    val code: String?,
    val latestData: Double?,
    val latestTime: Long?,
    val latitude: Double?,
    val longitude: Double?,
    val name: String?,
    val online: Int?,
    val sequence: Int?,
    val type: String?,
    val typeName: String?,
    val unit: String?,
) : Serializable

data class DeviceData(
    val id: String,
    val deviceId: String,
    val time: String,
    val data: Double,
    val convertData: String,
)