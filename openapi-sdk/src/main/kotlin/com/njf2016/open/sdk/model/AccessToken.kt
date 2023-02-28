package com.njf2016.open.sdk.model

import java.io.Serializable

/**
 * Access token 数据类
 *
 * @property accessToken 本体
 * @property expiresIn 过期时间
 * @constructor Create empty Access token
 * @author <a href=mailto:mcxinyu@foxmail.com>yuefeng</a> in 2023/2/14.
 */
data class AccessToken(
    val accessToken: String,
    val expiresIn: String,
) : Serializable