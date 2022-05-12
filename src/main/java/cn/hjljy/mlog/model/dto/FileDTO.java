package cn.hjljy.mlog.model.dto;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.enums.FileStorageTypeEnum;
import cn.hjljy.mlog.common.utils.MlogUtils;
import cn.hjljy.mlog.common.utils.SnowFlakeUtil;
import cn.hjljy.mlog.model.entity.MlogFiles;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.util.Calendar;

/**
 * 文件dto
 *
 * @author hjljy
 * @date 2021/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ToString
public class FileDTO extends MlogFiles {
    /**
     * 是否存在
     */
    private Boolean exist;

    public static MlogFiles convert2Entity(FileDTO dto) {
        MlogFiles article =new MlogFiles();
        BeanUtils.copyProperties(dto,article);
        return article;
    }
    public static final class Builder {

        private String fileName;

        private String fileType;

        private Long fileSize;

        private String fileBasePath;

        private String filePath;

        private FileStorageTypeEnum storage;

        public Builder setFileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder setFileBasePath(String fileBasePath) {
            this.fileBasePath = fileBasePath;
            return this;
        }

        public Builder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder setFileSize(Long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public Builder setStorage(FileStorageTypeEnum storage) {
            this.storage = storage;
            return this;
        }


        String getFullPath() {
            if (StringUtils.isNotBlank(this.fileBasePath)) {
                return this.fileBasePath+this.filePath;
            }
            return this.filePath;
        }

        String getFilePath(String fileName){
            //获取当前月份生成文件路径
            Calendar current = Calendar.getInstance();
            int year = current.get(Calendar.YEAR);
            int month = current.get(Calendar.MONTH) + 1;
            String monthString = month < 10 ? "0" + month : String.valueOf(month);
            return Constant.UPLOAD_PREFIX + year + File.separator + monthString + File.separator+fileName;
        }
        public FileDTO build() {
            //优先生成路径地址
            this.filePath = MlogUtils.changeFileSeparatorToUrlSeparator(getFilePath(this.fileName));
            this.fileBasePath =MlogUtils.changeFileSeparatorToUrlSeparator(MlogUtils.ensureSuffix(this.fileBasePath,"/"));
            FileDTO dto = new FileDTO();
            dto.setId(SnowFlakeUtil.createId());
            dto.setCreateTime(System.currentTimeMillis());
            dto.setFullPath(getFullPath());
            dto.setFileBasePath(this.fileBasePath);
            dto.setFilePath(MlogUtils.ensurePrefix(this.filePath,"/"));
            dto.setFileSize(this.fileSize);
            dto.setFileName(this.fileName);
            dto.setStorage(this.storage);
            dto.setFileType(this.fileType);
            dto.setExist(Boolean.FALSE);
            log.info("上传文件名称:{},文件类型:{},是否存在:{},存储方式:{},文件地址:{}",dto.getFileName(),dto.getFileType(),dto.getExist(),dto.getStorage(),dto.getFullPath());
            return dto;
        }
    }
}
