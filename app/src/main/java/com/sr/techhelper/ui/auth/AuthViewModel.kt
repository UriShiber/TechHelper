package com.sr.techhelper.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.data.users.UsersRepository
import com.sr.techhelper.utils.ImageUtils
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val usersRepository = UsersRepository()

    fun register(onFinishUi: () -> Unit, userImage: String) {
        viewModelScope.launch {
            usersRepository.upsertUser(UserModel.fromFirebaseAuth(userImage))
            onFinishUi()
        }
    }
}