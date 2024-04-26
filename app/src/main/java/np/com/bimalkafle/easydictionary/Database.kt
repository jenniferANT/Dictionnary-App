package com.example.sqldemo3

class DatabaseUser {


    var id: Int = 0
        private set
    var name: String? = null
        get() = field?:""
    var pass: String? = null
        get() = field?:""

    constructor() {
        // Hàm constructor không tham số
    }

    constructor(name: String, pass: String?) {
        this.name = name
        this.pass= pass

    }



//    init {
//        require(pass == cfpass) { "Password and confirm password do not match." }
//    }

    override fun toString(): String {
        return "DatabaseUser( id=$id name=$name,  pass=$pass)"
    }


}
