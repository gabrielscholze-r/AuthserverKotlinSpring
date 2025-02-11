package br.pucpr.authserver.course

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CourseService(
    val repository: CourseRepository
) {
    fun save(course: Course): Course = repository.save(course)
    fun findByIdOrNull(id: Long?): Course? = repository.findByIdOrNull(id)
    fun delete(id: Long) = repository.deleteById(id)
    fun findAll(): List<Course> = repository.findAll()
    fun deleteAll() = repository.deleteAll()
}