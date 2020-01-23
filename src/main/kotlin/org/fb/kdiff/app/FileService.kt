package org.fb.kdiff.app

import org.fb.kdiff.domain.DiffItem
import org.fb.kdiff.domain.PathRequest
import org.springframework.stereotype.Service
import java.io.File

@Service
class FileService {

    fun filesAt(pathRequest: PathRequest): List<DiffItem> {
        val leftFiles = fileList(pathRequest.leftRoot)
        val rightFiles = fileList(pathRequest.rightRoot)

        val merged = mutableSetOf<File>()
        merged.addAll(leftFiles)
        merged.addAll(rightFiles)

        return merged.map {
            val left = if (leftFiles.contains(it)) it else File(DiffItem.MISSING)
            val right = if (rightFiles.contains(it)) it else File(DiffItem.MISSING)
            DiffItem(left, right, pathRequest.leftRoot, pathRequest.rightRoot)
        }
    }

    private fun fileList(path: File): List<File> {
        return if (path.exists()) {
            path.walk()
                    .filter { it != path }
                    .map { it.relativeTo(path) }
                    .toList()
        } else
            listOf()
    }

    fun copyRight(diffItem: DiffItem): DiffItem {
        diffItem.copyRight()
        return diffItem
    }

    fun copyLeft(diffItem: DiffItem): DiffItem {
        diffItem.copyLeft()
        return diffItem
    }
}