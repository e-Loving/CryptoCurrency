package uz.eloving.cryptocurrency.presentation.coins_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.eloving.cryptocurrency.common.Recourse
import uz.eloving.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Recourse.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Recourse.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Recourse.Error -> {
                    _state.value =
                        CoinListState(error = result.message ?: "An unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }
}