package br.pucpr.authserver.course

import br.pucpr.authserver.course.request.AddLesson
import br.pucpr.authserver.exception.NotFoundException
import br.pucpr.authserver.lesson.Lesson
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
    fun addLesson(lesson: AddLesson) {
        val course = repository.findByIdOrNull(lesson.course) ?: throw NotFoundException("User ${lesson.course} not found")

        val newLesson = Lesson(
            name = lesson.name,
            duration = lesson.duration,
            course = course!!
        )
        course.lessons.add(newLesson)
        repository.save(course)
    }
    fun deleteLesson(id: Long, lessonId: Long) {
        val course = repository.findByIdOrNull(id) ?: throw NotFoundException("User ${id} not found")
        course.lessons.removeIf({ l: Lesson -> l.id == lessonId } )
        repository.save(course)
    }
}