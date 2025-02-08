package br.pucpr.authserver.course

import br.pucpr.authserver.lesson.Lesson
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "courses")
class Course (
    @Id @GeneratedValue
    val id: Long? = null,

    @NotBlank
    val name: String,

    @NotBlank
    val description: String,

    @OneToMany
    @JoinTable(
        name="Lessons",
        joinColumns = [JoinColumn(name = "idLesson")],
        inverseJoinColumns = [JoinColumn(name = "idLesson")]
    )
    val lessons: MutableSet<Lesson> = mutableSetOf()
)