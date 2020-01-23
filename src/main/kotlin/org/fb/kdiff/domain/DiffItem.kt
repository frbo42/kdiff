package org.fb.kdiff.domain

import java.io.File


data class DiffItem(var left: File, var right: File, val leftRoot: File, val rightRoot: File) : Comparable<DiffItem> {
    override fun compareTo(other: DiffItem): Int {
        return leftName.compareTo(other.leftName)
    }

    fun isRightEnabled(): Boolean {
        return isRightEmpty()
    }

    fun isLeftEnabled(): Boolean {
        return isLeftEmpty()
    }

    private fun isRightEmpty(): Boolean {
        return right.name == MISSING
    }

    private fun isLeftEmpty(): Boolean {
        return left.name == MISSING
    }

    fun copyLeft() {
        val target = leftRoot.resolve(right)
        rightRoot.resolve(right).copyTo(target)
        left = right
    }

    fun copyRight() {
        val target = rightRoot.resolve(left)
        leftRoot.resolve(left).copyTo(target)
        right = left
    }

    val leftName: String
        get() {
            return left.path
        }

    val rightName: String
        get() {
            return right.path
        }

    companion object {
        const val MISSING = "-"
    }
}