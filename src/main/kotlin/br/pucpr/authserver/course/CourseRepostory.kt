package br.pucpr.authserver.course

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepostory: JpaRepository<Course, Long>