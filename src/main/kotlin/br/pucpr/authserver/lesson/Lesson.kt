package br.pucpr.authserver.lesson

import br.pucpr.authserver.course.Course
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "tblLesson")
class Lesson(
    @Id @GeneratedValue
    val id: Long? = null,

    @NotBlank
    val name: String,

    @NotBlank
    val duration: Int,

    @ManyToOne
    @JoinTable(
        name="Course",
        joinColumns = [JoinColumn(name = "idCourse")],
        inverseJoinColumns = [JoinColumn(name = "idCourse")]
    )
    val course : Course,

    )