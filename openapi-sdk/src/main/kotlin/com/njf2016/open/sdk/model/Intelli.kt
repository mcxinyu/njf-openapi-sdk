package com.njf2016.open.sdk.model

import java.io.Serializable

/**
 * 网关数据类
 *
 * @property id 网关序号
 * @property concentrator 网关 ID
 * @property createTime 创建时间
 * @property updateTime 更新时间
 * @property deviceType 设备类型
 * @property lat 纬度
 * @property lng 经度
 * @property name 网关名称
 * @property online 在线状态，0 离线；1 离线
 * @property baud 波特率
 * @property period 上报周期
 * @property address 地址
 * @property sort 排序号
 * @constructor Create empty Concentrator
 */
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

/**
 * 设备数据类
 *
 * @property id
 * @property createtime
 * @property valid
 * @property concentrator 上层网关 ID
 * @property circuit
 * @property code 原始数据转单位格式的 js-code
 * @property latestData 最新数据
 * @property latestTime 最新数据上报时间
 * @property latitude 纬度
 * @property longitude 经度
 * @property name 设备名称
 * @property online 在线状态
 * @property sequence 排序号
 * @property type 类型
 * @property typeName 类型名称
 * @property unit 单位
 * @constructor Create empty Device
 */
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

/**
 * 设备数据的数据类
 *
 * @property id
 * @property deviceId 上层设备 ID
 * @property time 上报时间
 * @property data 原始数据
 * @property convertData 通过设备 [Device.code] 转换后的数据
 * @constructor Create empty Device data
 */
data class DeviceData(
    val id: String,
    val deviceId: String,
    val time: String,
    val data: Double,
    val convertData: String,
)