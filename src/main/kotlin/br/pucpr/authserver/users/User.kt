package br.pucpr.authserver.users

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "tbl_users")
class User(
    @Id @GeneratedValue
    var id: Long? = null,
    var name: String,
    @NotBlank
    var password: String,
    @Column(unique = true, nullable = false)
    var email: String
)
