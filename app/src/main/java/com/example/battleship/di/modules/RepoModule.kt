package com.example.battleship.di.modules

import com.example.battleship.model.repositories.SessionRepository
import com.example.battleship.model.repositories.UserRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()


    @Provides
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides
    fun provideUserRepository(fireStore: FirebaseFirestore) =
        UserRepository(fireStore)

    @Provides
    fun provideSessionRepository(firebaseDatabase: FirebaseDatabase) =
        SessionRepository(firebaseDatabase)

//    @Provides
//    fun providesGameResultRepository(fireStore: FirebaseFirestore) = GameResultRepository(fireStore)
}