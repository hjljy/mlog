package cn.hjljy.mlog.model.dto;

import cn.hjljy.mlog.model.enums.FileStorageTypeEnum;
import lombok.Data;

/**
 * 文件存储设置dto
 *
 * @author hjljy
 * @date 2021/12/31
 */
@Data
public class FileStorageSettingDTO {

    private FileStorageTypeEnum type;

    private String ossKey;

    private String ossPwd;
}
