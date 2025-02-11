package br.pucpr.authserver.lesson

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository: JpaRepository<Lesson, Long> {
    @Query("select distinct l from Lesson l" +
            " join l.course c"+
            " where c.id=:id" +
            " order by l.name")
    fun findAllByCourse(id: Long): List<Lesson>

}