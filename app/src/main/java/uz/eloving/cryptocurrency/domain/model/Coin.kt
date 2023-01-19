package uz.eloving.cryptocurrency.domain.model

import uz.eloving.cryptocurrency.data.remote.dto.CoinDto

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)

fun CoinDto.toCoin() = Coin(id, is_active, name, rank, symbol)
