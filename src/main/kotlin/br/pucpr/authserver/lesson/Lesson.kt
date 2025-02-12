package br.pucpr.authserver.lesson

import br.pucpr.authserver.course.Course
import com.fasterxml.jackson.annotation.JsonIgnore
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

//    @ManyToOne
    @JsonIgnore
    var course: Course

    )