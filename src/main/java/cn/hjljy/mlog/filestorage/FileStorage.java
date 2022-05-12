package cn.hjljy.mlog.filestorage;

import cn.hjljy.mlog.common.enums.FileStorageTypeEnum;
import cn.hjljy.mlog.model.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储接口 请勿直接注入使用  如需进行文件存储，请通过{FileStorageContext}策略类进行调用
 *
 * @see FileStorageContext
 * @author hjljy
 * @date 2021/12/28
 */
public interface FileStorage {


    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link FileDTO}
     */
    FileDTO uploadFile(MultipartFile file);

    /**
     * 获取文件存储类型
     *
     * @return {@link FileStorageTypeEnum}
     */
    FileStorageTypeEnum getFileStorageType();
}
