package br.pucpr.authserver.users

import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserSevice (
    val userRepository : UserRepository
){
    fun insert(user: User) : User{
        return userRepository.save(user);
    }
    fun findAll(sortDir: SortDir) : List<User> =
        when(sortDir){
            SortDir.ASC -> userRepository.findAll(Sort.by("name"))
            SortDir.DESC -> userRepository.findAll(Sort.by("name").descending())
        }

    fun getById(id: Long) : User? = userRepository.findByIdOrNull(id)

    fun delete(id: Long) = userRepository.deleteById(id)

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)
}