package com.example.data.core.utils

import com.example.data.local.prefs.TokenManager
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.ProtocolException

/**
 * [AuthInterceptor] Этот класс используется для добавления заголовка авторизации к запросу на сервер.
 * [tokenManager] - менеджер токенов, используемый для получения доступа к токенам авторизации.
 */
class AuthInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {

    /**
     * В методе [intercept] создается объект Request на основе полученного объекта Chain,
     * затем проверяется наличие заголовка авторизации в запросе. Если заголовок не установлен,
     * то берется токен доступа из [tokenManager], и заголовок добавляется к запросу.
     *
     * Далее, если возникает исключение ProtocolException, то возвращается фиктивный объект
     * Response с пустым телом и кодом 200. В противном случае, метод proceed() продолжает обработку
     * запроса и возвращает объект Response.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        if (request.header("Authorization") == null) {
            tokenManager.accessToken?.let { token ->
                builder.addHeader(
                    "Authorization",
                    "Bearer $token"
                )
            }
        }
        return try {
            chain.proceed(builder.build())
        } catch (e: ProtocolException) {
            val emptyBody = "".toResponseBody("text/plain".toMediaTypeOrNull())
            ResponseBody

            return chain.proceed(builder.build())
                .newBuilder()
                .code(200)
                .body(emptyBody)
                .build()
        }
    }
}