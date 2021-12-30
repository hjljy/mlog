package cn.hjljy.mlog.filestorage;

import cn.hjljy.mlog.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储
 *
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


}
