package uz.eloving.cryptocurrency.data.repository

import uz.eloving.cryptocurrency.data.remote.CoinPaprikaApi
import uz.eloving.cryptocurrency.data.remote.dto.CoinDetailDto
import uz.eloving.cryptocurrency.data.remote.dto.CoinDto
import uz.eloving.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}