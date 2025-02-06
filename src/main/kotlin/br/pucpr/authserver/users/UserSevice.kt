package br.pucpr.authserver.users

import org.springframework.stereotype.Service

@Service
class UserSevice (
    val userRepository : UserRepository
){
    fun insert(user: User) : User{
        return userRepository.save(user);
    }
    fun findAll() : List<User> = userRepository.findAll()

    fun getById(id: Long) : User? = userRepository.getById(id)

    fun delete(id: Long): User? = userRepository.delete(id)

    fun getSorted(sortDir: String) = userRepository.findAllSorted(sortDir)
}