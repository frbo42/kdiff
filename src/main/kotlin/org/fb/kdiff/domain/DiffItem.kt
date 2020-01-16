package org.fb.kdiff.domain


data class DiffItem(val left: String, var right: String) : Comparable<DiffItem> {
    override fun compareTo(other: DiffItem): Int {
        return left.compareTo(other.left)
    }
}