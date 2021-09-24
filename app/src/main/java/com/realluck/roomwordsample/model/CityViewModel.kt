package com.realluck.roomwordsample

import androidx.lifecycle.*
import com.realluck.roomwordsample.db.CityRepository
import com.realluck.roomwordsample.model.City
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CityViewModel(private val repository: CityRepository): ViewModel()
{
    private var sortBy: MutableLiveData<String> = MutableLiveData("asc")

    var cityListLiveData: LiveData<List<City>> = Transformations.switchMap(sortBy){
        asc -> repository.allWords(asc).asLiveData()
    }

    fun sort(asc: String){
        sortBy.value = asc
    }

    fun insert(city: City) = viewModelScope.launch {
        repository.insert(city)
    }

}

class CityViewModelFactory(private val repository: CityRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}