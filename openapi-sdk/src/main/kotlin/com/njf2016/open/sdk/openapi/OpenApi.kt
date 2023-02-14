package com.njf2016.open.sdk.openapi

import com.njf2016.open.sdk.http.Http
import com.njf2016.open.sdk.http.host
import com.njf2016.open.sdk.http.httpClient
import com.njf2016.open.sdk.model.*
import com.njf2016.open.sdk.utils.simple_yyyyMMddHHmmss
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import java.nio.file.AccessDeniedException
import java.util.*
import javax.naming.AuthenticationException

/**
 * @author <a href=mailto:mcxinyu@foxmail.com>yuefeng</a> in 2023/2/12.
 */
object OpenApi {
    private var _accessToken: AccessToken? = null

    /**
     * 限制请求次数
     */
    private var `x-ratelimit-limit`: Long? = null

    /**
     * 剩余请求次数
     */
    private var `x-ratelimit-remaining`: Long? = null

    /**
     * 剩余重置时间
     */
    private var `x-ratelimit-reset`: Long? = null

    @JvmStatic
    fun init(appId: String, secret: String) = apply { Http.generateBasicAuth(appId, secret) }

    private fun baseCheck(it: HttpResponse) {
        if (it.status == HttpStatusCode.Unauthorized) {
            throw AuthenticationException("认证失败，请检查 appId 和 secret")
        }

        if (it.status == HttpStatusCode.Forbidden) {
            throw AccessDeniedException("禁止请求，请在农卷风开放平台绑定服务器 IP")
        }

        if (it.status == HttpStatusCode.TooManyRequests) {
            throw AccessDeniedException("请求频率超过限制")
        }

        parseRatelimit(it.headers)
    }

    private fun parseRatelimit(headers: Headers) = runCatching {
        `x-ratelimit-limit` = headers["x-ratelimit-limit"]?.toLong()
        `x-ratelimit-remaining` = headers["x-ratelimit-remaining"]?.toLong()
        `x-ratelimit-reset` = headers["x-ratelimit-reset"]?.toLong()
    }

    @JvmStatic
    @JvmOverloads
    fun getAccessToken(refresh: Boolean = false): String? {
        val dateResult = runCatching { simple_yyyyMMddHHmmss.parse(_accessToken?.expiresIn) }

        if (refresh || _accessToken == null || _accessToken?.accessToken?.isEmpty() == true ||
            // 提前 30 分钟刷新 token
            dateResult.isFailure || dateResult.getOrNull()?.after(Date(Date().time - 30 * 60 * 1000)) == true
        ) {
            if (Http.basicAuthCredentials == null) {
                throw AuthenticationException("请先调用 OpenApi.init() 方法配置 appId 和 secret。并确认已经在农卷风开放平台绑定服务器 IP。")
            }

            accessToken {
                runBlocking {
                    baseCheck(it)

                    _accessToken = it.body<ResponseModel<AccessToken>>().content
                }
            }
        }

        return _accessToken?.accessToken
    }

    @JvmStatic
    @JvmOverloads
    fun getIntelliConcentrators(page: Int = 1, num: Int = 16): List<Concentrator>? {
        var list: List<Concentrator>? = null
        intelliConcentrators(page, num) {
            list = runBlocking {
                baseCheck(it)
                it.body<ResponseModel<List<Concentrator>>>()
            }.content
        }
        return list
    }

    @JvmStatic
    fun getIntelliDevices(concentrator: String): List<Device>? {
        var list: List<Device>? = null
        intelliDevices(concentrator) {
            list = runBlocking {
                baseCheck(it)
                it.body<ResponseModel<List<Device>>>()
            }.content
        }
        return list
    }

    @JvmStatic
    @JvmOverloads
    fun getIntelliDeviceData(
        deviceId: String,
        latest: Boolean? = false,
        time: String? = null,
        startTime: String? = null, endTime: String? = null,
        page: Int = 1, num: Int = 16
    ): List<DeviceData>? {
        var list: List<DeviceData>? = null
        intelliDeviceData(deviceId, latest, time, startTime, endTime, page, num) {
            list = runBlocking {
                baseCheck(it)
                it.body<ResponseModel<List<DeviceData>>>()
            }.content
        }
        return list
    }
}

/**
 * 获取 accessToken
 *
 * @param result Function1<HttpResponse, Unit>
 */
fun accessToken(result: (HttpResponse) -> Unit) =
    runBlocking { result.invoke(httpClient.get("$host/api/auth/token")) }

fun authTest(result: (HttpResponse) -> Unit) =
    runBlocking { result.invoke(httpClient.get("$host/api/auth/test")) }

/**
 * 读取网关列表
 *
 * @param page Int 分页页码（从 1 开始）
 * @param num Int 分页内条目数量（最大 100）
 * @param result Function1<HttpResponse, Unit>
 */
fun intelliConcentrators(page: Int = 1, num: Int = 16, result: (HttpResponse) -> Unit) =
    runBlocking {
        result.invoke(httpClient.get("$host/api/intelli/concentrators") {
            parameter("page", page)
            parameter("num", num)
        })
    }

/**
 * 读取传感器列表
 *
 * @param concentrator String 网关编号
 * @param result Function1<HttpResponse, Unit>
 */
fun intelliDevices(concentrator: String, result: (HttpResponse) -> Unit) =
    runBlocking {
        result.invoke(httpClient.get("$host/api/intelli/devices") {
            parameter("concentrator", concentrator)
        })
    }

/**
 * 读取传感器数据
 *
 * 如果 latest、time、startTime、endTime 都没有传参的话，会返回当天整天数据。
 *
 * @param deviceId String 传感器设备编号
 * @param latest Boolean? （可选，优先级最高）读取最后一次有数据上报的当天整天数据
 * @param time String? （可选，优先级第二）指定读取某一天的数据，支持 yyyy-MM-dd
 * @param startTime String? （可选，优先级第三）按时间区间读取数据的开始时间，支持 yyyy-MM-dd 和 yyyy-MM-dd HH:mm:ss 两种格式
 * @param endTime String? （可选，优先级第三）按时间区间读取数据的结束时间，支持 yyyy-MM-dd 和 yyyy-MM-dd HH:mm:ss 两种格式
 * @param page Int
 * @param num Int
 * @param result Function1<HttpResponse, Unit>
 */
fun intelliDeviceData(
    deviceId: String,
    latest: Boolean? = false,
    time: String? = null,
    startTime: String? = null, endTime: String? = null,
    page: Int = 1, num: Int = 16,
    result: (HttpResponse) -> Unit
) = runBlocking {
    result.invoke(httpClient.get("$host/api/intelli/data") {
        parameter("deviceId", deviceId)
        parameter("latest", latest)
        parameter("time", time)
        parameter("startTime", startTime)
        parameter("endTime", endTime)
        parameter("page", page)
        parameter("num", num)
    })
}
