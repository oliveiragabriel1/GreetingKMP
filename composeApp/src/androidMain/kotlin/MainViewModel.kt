import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _greetingList = MutableStateFlow<List<String>>(listOf())
    private val _image = MutableStateFlow<String>("")
    val greetingList: StateFlow<List<String>> get() = _greetingList
    val image: StateFlow<String> get() = _image
    init {
        viewModelScope.launch {
            Greeting().greet().collect { phrase  ->
                _greetingList.update { list -> list + phrase }
            }
        }
    }
}