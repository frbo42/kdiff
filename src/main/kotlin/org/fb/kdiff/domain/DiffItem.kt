package org.fb.kdiff.domain

import java.io.File


data class DiffItem(val left: File, var right: File) : Comparable<DiffItem> {
    override fun compareTo(other: DiffItem): Int {
        return leftName.compareTo(other.leftName)
    }

    fun isRightEnabled(): Boolean {
        return leftName != rightName
    }

    val leftName: String
        get() {
            return left.name
        }

    val rightName: String
        get() {
            return right.name
        }
}