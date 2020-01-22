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

        val merged = mutableSetOf<String>()
        merged.addAll(leftFiles)
        merged.addAll(rightFiles)

        return merged.map {
            val left = if (leftFiles.contains(it)) it else DiffItem.MISSING
            val right = if (rightFiles.contains(it)) it else DiffItem.MISSING
            DiffItem(File(pathRequest.leftRoot, left), File(pathRequest.rightRoot, right))
        }
    }

    private fun fileList(path: File): List<String> {
        return if (path.exists()) {
            path.list()
                    .asList()
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