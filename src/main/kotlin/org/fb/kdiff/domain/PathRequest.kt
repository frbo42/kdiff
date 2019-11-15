package org.fb.kdiff.domain

import java.nio.file.Path

data class  PathRequest(val leftRoot: Path, val rightRoot:Path, val subPath: Path )