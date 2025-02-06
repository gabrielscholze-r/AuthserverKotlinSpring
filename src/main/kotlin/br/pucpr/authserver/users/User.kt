package br.pucpr.authserver.users

class User(
    var id: Long? = null,
    var name: String,
    var password: String,
    var email: String
) {

}