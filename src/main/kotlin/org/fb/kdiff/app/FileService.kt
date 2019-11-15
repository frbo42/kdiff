package org.fb.kdiff.app

import org.fb.kdiff.domain.DiffItem
import org.fb.kdiff.domain.PathRequest
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

@Service
class FileService {

    fun filesAt(pathRequest: PathRequest): List<DiffItem> {
        val leftFiles = fileList(pathRequest.leftRoot)
        val rightFiles = fileList(pathRequest.rightRoot)
        val merged = mutableSetOf<Path>()
        merged.addAll(leftFiles)
        merged.addAll(rightFiles)

        return merged.map {
            val left = if (leftFiles.contains(it)) it.fileName.toString() else "-"
            val right = if (rightFiles.contains(it)) it.fileName.toString() else "-"
            DiffItem(left, right)
        }
    }

    private fun fileList(path: Path): List<Path> {
        return if (Files.exists(path)) Files.list(path).sorted().toList() else listOf()
    }
}