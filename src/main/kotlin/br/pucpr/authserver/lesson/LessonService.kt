package br.pucpr.authserver.lesson

import br.pucpr.authserver.course.Course
import org.springframework.stereotype.Service


@Service
class LessonService(
    val repository: LessonRepository
) {
    fun save(lesson: Lesson) = repository.save(lesson)
    fun findAllByCourse(course: Long): List<Lesson>? = repository.findAllByCourse(course)
    fun delete(id: Long) = repository.deleteById(id)
}