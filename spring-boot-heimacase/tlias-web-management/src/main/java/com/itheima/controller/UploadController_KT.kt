package com.itheima.controller

import com.itheima.pojo.Result_KT
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID
import kotlin.io.path.Path

/**
 * @author uncle_yumo
 * @fileName UploadController_KT
 * @createDate 2024/10/14 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Slf4j
@RestController
class UploadController_KT {

    @PostMapping("/upload")
    fun upload(
        username: String?,
        age: Int?,
        image: MultipartFile?
    ): Result_KT {

        val logger = LoggerFactory.getLogger(UploadController_KT::class.java)
        val originalFileName = image?.originalFilename
        logger.info("文件上传 | 用户名[$username] | 年龄[$age] | 文件名[${originalFileName}]")

        val indexOfSuffix = originalFileName?.lastIndexOf(".")
        if (indexOfSuffix == -1) return Result_KT.error("文件名不合法 || 请上传正确的文件")
        val fileExtension = originalFileName?.substring(indexOfSuffix!!)

        val newFileName = "${UUID.randomUUID()}$fileExtension"

        // 项目路径
        val projectPath = Paths.get(System.getProperty("user.dir")).toAbsolutePath()
        val pathToSave = projectPath.resolve("tlias-web-management/src/main/resources/tempfile/warehouse").resolve(newFileName)
        image?.transferTo(Path(pathToSave.toString()))

        return Result_KT.success("修改后的文件拓展名 | [$newFileName]")
    }
}