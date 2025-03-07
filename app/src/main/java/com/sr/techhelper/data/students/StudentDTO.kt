package com.sr.techhelper.data.students

data class StudentDTO(
 val id: String? = null,
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    var isChecked: Boolean = false,
    val image : String? = null,
) {
    fun toStudentModel(): StudentModel {
        return StudentModel(
            id=id ?: "",
            name = name,
            address = address,
            phone = phone,
            isChecked = isChecked,
            image = image
        )
    }
}