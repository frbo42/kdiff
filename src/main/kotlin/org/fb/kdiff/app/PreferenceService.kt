package org.fb.kdiff.app

import org.fb.kdiff.domain.PathRequest
import org.springframework.stereotype.Service
import java.io.File
import java.util.prefs.Preferences

@Service
class PreferenceService {
    private val prefs = Preferences.userRoot().node("kdiff")

    fun initialPath(): PathRequest {
        return PathRequest(
                File(prefs.get(LEFT_ROOT, HOME_FOLDER)),
                File(prefs.get(RIGHT_ROOT, HOME_FOLDER))
        )
    }

    fun saveLeftPath(folder: File) {
        prefs.put(LEFT_ROOT, folder.canonicalPath)
    }

    fun saveRightPath(folder: File) {
        prefs.put(RIGHT_ROOT, folder.canonicalPath)
    }

    companion object {
        private const val LEFT_ROOT = "leftRoot"
        private const val RIGHT_ROOT = "rightRoot"
        private val HOME_FOLDER = System.getProperty("user.home")
    }
}