package org.d3if3007.gotix.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gotix")
data class GotixEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var kuota: Int,
    var film: String
)
