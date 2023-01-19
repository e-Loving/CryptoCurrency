package uz.eloving.cryptocurrency.domain.use_case.get_coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import uz.eloving.cryptocurrency.common.Recourse
import uz.eloving.cryptocurrency.data.remote.dto.CoinDetailDto
import uz.eloving.cryptocurrency.data.remote.dto.toCoinDetail
import uz.eloving.cryptocurrency.domain.model.Coin
import uz.eloving.cryptocurrency.domain.model.CoinDetail
import uz.eloving.cryptocurrency.domain.model.toCoin
import uz.eloving.cryptocurrency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Recourse<CoinDetail>> = flow {
        try {
            emit(Recourse.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Recourse.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Recourse.Error<CoinDetail>(e.localizedMessage ?: "An expected error occurred"))
        } catch (e: IOException) {
            emit(Recourse.Error<CoinDetail>("Couldn't reach server. Check your internet connection"))
        }
    }
}