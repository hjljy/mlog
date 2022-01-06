package cn.hjljy.mlog.filestorage;


import cn.hjljy.mlog.model.enums.FileStorageTypeEnum;
import cn.hjljy.mlog.config.MlogProperties;
import cn.hjljy.mlog.model.dto.FileDTO;
import cn.hutool.core.io.FileTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 本地文件存储具体实现  如需调用请通过 {FileStorageContext} 策略类进行调用 不建议直接进行调用
 *
 * @see FileStorageContext
 * @author hjljy
 */
@Slf4j
@Component
public class LocalFileStorage implements FileStorage {

    private final MlogProperties mlogProperties;

    public LocalFileStorage(MlogProperties mlogProperties) {
        this.mlogProperties = mlogProperties;
        checkWorkDir();
    }

    /**
     * 检查工作目录是否存在
     */
    private void checkWorkDir() {
        String workDir = mlogProperties.getWorkDir();
        Path workPath = Paths.get(mlogProperties.getWorkDir());
        if (!Files.isDirectory(workPath)
                || !Files.isReadable(workPath)
                || !Files.isWritable(workPath)) {
            log.warn("请确保工作目录 {} 是一个文件夹, 并且拥有读写的操作权限", workDir);
        }
    }

    @Override
    public FileDTO uploadFile(MultipartFile file) {
        Assert.notNull(file, "上传的文件不能为空");
        String workDir = mlogProperties.getWorkDir();
        String type = null;
        try {
            type = FileTypeUtil.getType(file.getInputStream());
        } catch (IOException e) {
            log.warn("获取上传的文件类型失败：{}",e.getMessage());
        }
        //通过文件构造器，传入基本参数即可统一构建
        FileDTO fileDTO = new FileDTO.Builder()
                .setFileName(file.getOriginalFilename())
                .setFileSize(file.getSize())
                .setStorage(getFileStorageType())
                .setFileBasePath(workDir)
                .setFileType(type)
                .build();
        Path localFileFullPath = Paths.get(fileDTO.getFullPath());
        try {
            File toFile = localFileFullPath.toFile();
            if (toFile.exists()) {
                fileDTO.setExist(Boolean.TRUE);
                log.info("上传文件失败:{}文件已存在", fileDTO.getFullPath());
            } else {
                Files.createDirectories(localFileFullPath.getParent());
                Files.createFile(localFileFullPath);
                file.transferTo(localFileFullPath);
            }
        } catch (IOException e) {
            log.error("上传文件失败:错误信息:{}", e.getMessage());
            e.printStackTrace();
        }
        return fileDTO;
    }

    @Override
    public FileStorageTypeEnum getFileStorageType() {
        return FileStorageTypeEnum.LOCAL;
    }
}
