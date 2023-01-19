package uz.eloving.cryptocurrency.domain.use_case.get_coins

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import uz.eloving.cryptocurrency.common.Recourse
import uz.eloving.cryptocurrency.domain.model.Coin
import uz.eloving.cryptocurrency.domain.model.toCoin
import uz.eloving.cryptocurrency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Recourse<List<Coin>>> = flow {
        try {
            emit(Recourse.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Recourse.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Recourse.Error<List<Coin>>(e.localizedMessage ?: "An expected error occurred"))
        } catch (e: IOException) {
            emit(Recourse.Error<List<Coin>>("Couldn't reach server. Check your internet connection"))
        }
    }
}