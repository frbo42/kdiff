package org.fb.kdiff.domain

import java.io.File

data class PathRequest(val leftRoot: File, val rightRoot: File)