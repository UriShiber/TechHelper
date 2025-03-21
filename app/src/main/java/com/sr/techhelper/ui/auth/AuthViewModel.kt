package com.sr.techhelper.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.data.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val usersRepository = UsersRepository()
    fun register(onFinishUi: () -> Unit, userImage: String) {
        viewModelScope.launch(Dispatchers.Main) {
            usersRepository.upsertUser(UserModel.fromFirebaseAuth(userImage))
            onFinishUi()
        }
    }
}