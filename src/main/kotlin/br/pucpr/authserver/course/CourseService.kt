package br.pucpr.authserver.course

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CourseService(
    val repostory: CourseRepostory
) {
    fun save(course: Course): Course = repostory.save(course)
    fun findByIdOrNull(id: Long?): Course? = repostory.findByIdOrNull(id)
    fun delete(id: Long) = repostory.deleteById(id)
}