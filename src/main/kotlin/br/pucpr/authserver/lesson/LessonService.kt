package br.pucpr.authserver.lesson

import org.springframework.stereotype.Service


@Service
class LessonService(
    val repository: LessonRepository
) {
    fun save(lesson: Lesson) = repository.save(lesson)
    fun findAllByCourse(dir: SortDir, course: Long): List<Lesson>? {
        val result = repository.findAllByCourse(course)
        return when(dir)
        {
            SortDir.ASC -> result.sortedBy { it.id }
            SortDir.DESC -> result.sortedByDescending { it.id }
        }
    }
    fun delete(id: Long) = repository.deleteById(id)
}