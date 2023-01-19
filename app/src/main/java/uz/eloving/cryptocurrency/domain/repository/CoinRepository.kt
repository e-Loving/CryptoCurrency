package uz.eloving.cryptocurrency.domain.repository

import uz.eloving.cryptocurrency.data.remote.dto.CoinDetailDto
import uz.eloving.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}