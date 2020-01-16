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

        val merged = mutableSetOf<String>()
        merged.addAll(leftFiles)
        merged.addAll(rightFiles)

        return merged.map {
            val left = if (leftFiles.contains(it)) it else Companion.MISSING
            val right = if (rightFiles.contains(it)) it else Companion.MISSING
            DiffItem(left, right)
        }
    }

    private fun fileList(path: Path): List<String> {
        return if (Files.exists(path)) {
            Files.list(path)
                    .map { it.fileName.toString() }
                    .toList()
        } else
            listOf()
    }

    fun copyRight(diffItem: DiffItem): DiffItem {
        diffItem.right = diffItem.left
        val source = Path.of("/home/frank/development/frbo/kotlin/kdiff_pics/mimacom")
        val target = Path.of("/home/frank/development/frbo/kotlin/kdiff_pics/target")
        val sourcePath = source.resolve(diffItem.left)
        val targetPath = target.resolve(diffItem.right)
        sourcePath.toFile().copyTo(targetPath.toFile())
        return diffItem
    }

    companion object {
        private const val MISSING = "-"
    }
}