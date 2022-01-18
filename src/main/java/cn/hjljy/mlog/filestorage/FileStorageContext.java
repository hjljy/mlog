package cn.hjljy.mlog.filestorage;

import cn.hjljy.mlog.common.support.ResultCode;
import cn.hjljy.mlog.common.support.ResultInfo;
import cn.hjljy.mlog.model.enums.FileStorageTypeEnum;
import cn.hjljy.mlog.model.dto.FileDTO;
import cn.hjljy.mlog.exception.MlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件存储策略使用类  通过本类根据传入的存储类型调用具体的实现方式
 *
 * @author hjljy
 */

@Slf4j
@Component
public class FileStorageContext {

    private final ConcurrentHashMap<FileStorageTypeEnum, FileStorage> fileStorages =
            new ConcurrentHashMap<>(16);

    public FileStorageContext(ApplicationContext applicationContext) {
        Map<String, FileStorage> fileStorageMap = applicationContext.getBeansOfType(FileStorage.class);
        initFileStorageBean(fileStorageMap);
    }

    @NonNull
    public void initFileStorageBean(@Nullable Map<String, FileStorage> fileStorageMap) {
        if (!CollectionUtils.isEmpty(fileStorageMap)) {
            for (FileStorage storage : fileStorageMap.values()) {
                fileStorages.put(storage.getFileStorageType(), storage);
            }
        }
    }

    public FileDTO uploadFile(MultipartFile file, FileStorageTypeEnum type) {
        FileStorage fileStorage = getByFileStorageType(type);
        return fileStorage.uploadFile(file);
    }

    public FileStorage getByFileStorageType(FileStorageTypeEnum type) {
        FileStorage fileStorage = fileStorages.get(type);
        if (null == fileStorage) {
            throw new MlogException(ResultCode.FILE_STORAGE_NOT_EXISTS);
        }
        return fileStorage;
    }
}
