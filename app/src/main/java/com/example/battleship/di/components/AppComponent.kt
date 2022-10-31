package com.example.battleship.di.components

import com.example.battleship.di.modules.RepoModule
import com.example.battleship.model.repositories.SessionRepository
import com.example.battleship.model.repositories.UserRepository
import dagger.Component

@Component(
    modules = [
        RepoModule::class,
    ]
)
interface AppComponent {
    val userRepository:UserRepository
    val sessionRepository:SessionRepository
}