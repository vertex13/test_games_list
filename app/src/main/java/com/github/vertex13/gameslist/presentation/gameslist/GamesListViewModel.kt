package com.github.vertex13.gameslist.presentation.gameslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vertex13.gameslist.domain.*
import com.github.vertex13.gameslist.usecases.GetGames
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val PAGE_SIZE = 5
private const val ERROR_DURATION = 3000L

class GamesListViewModel(
    private val getGames: GetGames
) : ViewModel() {

    private val _mostAnticipatedCategory = MutableLiveData(CategoryData(MostAnticipatedCategory))
    private val _latestCategory = MutableLiveData(CategoryData(LatestCategory))
    private val _mostRated2020Category = MutableLiveData(CategoryData(MostRatedCategory(2020)))
    private val _loadingError = MutableLiveData(false)

    val mostAnticipatedCategory: LiveData<CategoryData> get() = _mostAnticipatedCategory
    val latestCategory: LiveData<CategoryData> get() = _latestCategory
    val mostRated2020Category: LiveData<CategoryData> get() = _mostRated2020Category
    val loadingError: LiveData<Boolean> get() = _loadingError

    init {
        loadMostAnticipatedGames()
        loadLatestGames()
        loadMostRated2020Games()
    }

    fun loadMostAnticipatedGames() = loadGames(_mostAnticipatedCategory)

    fun loadLatestGames() = loadGames(_latestCategory)

    fun loadMostRated2020Games() = loadGames(_mostRated2020Category)

    private fun loadGames(categoryData: MutableLiveData<CategoryData>) {
        val category = categoryData.value
        if (category == null || category.isLoading || category.isAllDataLoaded) {
            return
        }
        categoryData.value = category.copy(isLoading = true)

        viewModelScope.launch {
            val page = try {
                getGames(category.gameCategory, category.games.size / PAGE_SIZE, PAGE_SIZE)
            } catch (e: Exception) {
                categoryData.value = category.copy(isLoading = false)
                setLoadingError()
                return@launch
            }

            categoryData.value = category.copy(
                isLoading = false,
                games = category.games + page.content,
                isAllDataLoaded = page.isLast
            )
        }
    }

    private fun setLoadingError() {
        if (_loadingError.value == true) {
            return
        }
        _loadingError.value = true
        viewModelScope.launch {
            delay(ERROR_DURATION)
            _loadingError.value = false
        }
    }

}

class CategoryData(
    val gameCategory: GameCategory,
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val isAllDataLoaded: Boolean = false
) {
    fun copy(
        isLoading: Boolean = this.isLoading,
        games: List<Game> = this.games,
        isAllDataLoaded: Boolean = this.isAllDataLoaded
    ) = CategoryData(this.gameCategory, isLoading, games, isAllDataLoaded)
}
