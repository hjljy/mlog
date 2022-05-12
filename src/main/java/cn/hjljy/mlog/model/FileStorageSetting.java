package cn.hjljy.mlog.model;

import cn.hjljy.mlog.common.enums.FileStorageTypeEnum;
import lombok.Data;

/**
 * 文件存储设置dto
 *
 * @author hjljy
 * @date 2021/12/31
 */
@Data
public class FileStorageSetting {

    private FileStorageTypeEnum type;

    private String ossKey;

    private String ossPwd;
}
