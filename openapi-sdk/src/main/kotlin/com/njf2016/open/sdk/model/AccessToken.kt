package com.njf2016.open.sdk.model

import java.io.Serializable

/**
 * @author <a href=mailto:mcxinyu@foxmail.com>yuefeng</a> in 2023/2/14.
 */
data class AccessToken(
    val accessToken: String,
    val expiresIn: String,
) : Serializable