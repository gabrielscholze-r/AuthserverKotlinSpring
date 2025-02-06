package br.pucpr.authserver.users

import org.springframework.stereotype.Component

@Component
class UserRepository {
    private val users = mutableMapOf<Long, User>()

    fun save (user: User) : User {
        if (user.id == null) {
            lastId += 1
            user.id = lastId
        }
        users[user.id!!] = user
        return user
    }

    fun findAll(): List<User> = users.values.sortedBy { it.name }

    fun getById(id: Long): User? = users[id]

    fun delete(id: Long): User? = users.remove(id)

    fun findAllSorted(sortDir : String): List<User> {
        if (sortDir.lowercase()=="desc") {
            return users.values.sortedByDescending { it.name }
        }
        return users.values.sortedBy { it.name }
    }

    companion object{
        var lastId : Long=0L
    }
}