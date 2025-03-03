package com.sr.techhelper.data.students;

import androidx.room.ColumnInfo
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.UUID

@Entity(
    tableName = "students"
)
data class StudentModel(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "isChecked") var isChecked: Boolean,
    @ColumnInfo(name = "image") val image : String? = null,
) {
    fun toStudentDto(): StudentDTO {
        return StudentDTO(
            id=id,
            name = name,
            address = address,
            phone = phone,
            isChecked = isChecked,
            image = image
        )
    }
}
