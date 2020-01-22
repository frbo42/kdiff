package org.fb.kdiff.domain

import java.io.File


data class DiffItem(var left: File, var right: File) : Comparable<DiffItem> {
    override fun compareTo(other: DiffItem): Int {
        return leftName.compareTo(other.leftName)
    }

    fun isRightEnabled(): Boolean {
        return leftName != rightName
    }

    fun copyLeft() {
        val parent = left.parent
        val name = right.name
        left = File(parent, name)

        right.copyTo(left)
    }

    fun copyRight() {
        val parent = right.parent
        val name = left.name
        right = File(parent, name)

        left.copyTo(right)
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