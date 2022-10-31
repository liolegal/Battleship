package com.example.battleship.model.repositories

import com.example.battleship.model.entities.User
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository( val fireStore: FirebaseFirestore) {

    fun getUser(id: String, callback: (ref: DocumentReference) -> Unit) {
        fireStore.collection("users")
            .whereEqualTo("userId", id)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful && it.result != null) {
                    val docs = it.result!!.documents

                    if (docs.size != 0) {
                        callback(docs[0].reference)
                    } else {
                        fireStore.collection("users")
                            .add(User(id, "Player"))
                            .addOnSuccessListener {
                                getUser(id, callback)
                            }
                    }
                }
            }
    }

}