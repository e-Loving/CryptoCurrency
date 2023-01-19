package uz.eloving.cryptocurrency.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import uz.eloving.cryptocurrency.data.remote.dto.CoinDetailDto
import uz.eloving.cryptocurrency.data.remote.dto.CoinDto

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}