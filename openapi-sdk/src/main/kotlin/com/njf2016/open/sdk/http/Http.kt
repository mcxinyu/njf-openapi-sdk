package com.njf2016.open.sdk.http

import com.njf2016.open.sdk.openapi.OpenApi
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.jackson.*

/**
 * @author <a href=mailto:mcxinyu@foxmail.com>yuefeng</a> in 2023/2/12.
 */
internal object Http {
    private var _basicAuthCredentials: BasicAuthCredentials? = null

    val basicAuthCredentials get() = _basicAuthCredentials

    fun generateBasicAuth(appId: String, secret: String) {
        _basicAuthCredentials = BasicAuthCredentials(appId, secret)
    }

    val client: HttpClient = HttpClient(OkHttp) {
        expectSuccess = true
        install(Logging)
        install(ContentNegotiation) {
            jackson()
        }
        install(HttpCookies)
        install(Auth) {
            basic {
                realm = "njf-open-service"
                credentials {
                    _basicAuthCredentials
                }
                // 在初始请求中启用发送凭据，而无需等待带有标头的（未经授权的）响应。
                sendWithoutRequest {
                    it.url.toString() == "$host/api/auth/token"
                }
            }
            bearer {
                realm = "njf-open-access-token"
                loadTokens {
                    val accessToken = OpenApi.getAccessToken()!!
                    BearerTokens(accessToken, "")
                }
                refreshTokens {
                    val accessToken = OpenApi.getAccessToken()!!
                    BearerTokens(accessToken, "")
                }
                // 在初始请求中启用发送凭据，而无需等待带有标头的（未经授权的）响应。
                sendWithoutRequest {
                    it.url.host == "open.njf2016.com" && it.url.toString() != "$host/api/auth/token"
                }
            }
        }
        engine {
            config {
                followRedirects(true)
                followSslRedirects(true)
            }
        }
    }
}

internal val httpClient: HttpClient get() = Http.client

internal const val host = "https://open.njf2016.com"