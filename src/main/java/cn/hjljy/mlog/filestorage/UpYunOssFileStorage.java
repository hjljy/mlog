package cn.hjljy.mlog.filestorage;

import cn.hjljy.mlog.common.enums.FileStorageTypeEnum;
import cn.hjljy.mlog.model.dto.FileDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 又拍云系统文件存储  如需调用请通过 {FileStorageContext} 策略类进行调用 不建议直接进行调用
 * @see FileStorageContext
 * @author hjljy
 * @date 2021/12/31
 */
@Component
public class UpYunOssFileStorage implements FileStorage{
    @Override
    public FileDTO uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public FileStorageTypeEnum getFileStorageType() {
        return FileStorageTypeEnum.UY;
    }
}
