package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.model.setting.FileStorageSetting;
import cn.hjljy.mlog.model.dto.FileDTO;
import cn.hjljy.mlog.model.entity.MlogFiles;
import cn.hjljy.mlog.filestorage.FileStorageContext;
import cn.hjljy.mlog.mapper.MlogFilesMapper;
import cn.hjljy.mlog.service.IMlogFilesService;
import cn.hjljy.mlog.service.IMlogSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-28
 */
@Service
public class MlogFilesServiceImpl extends ServiceImpl<MlogFilesMapper, MlogFiles> implements IMlogFilesService {


    private final FileStorageContext fileStorageContext;

    private final IMlogSettingService settingService;

    public MlogFilesServiceImpl(FileStorageContext fileStorageContext, IMlogSettingService settingService) {
        this.fileStorageContext = fileStorageContext;
        this.settingService = settingService;
    }

    @Override
    public FileDTO uploadFile(MultipartFile file) {
        FileStorageSetting setting = settingService.getFileStorageSetting();
        FileDTO uploadFile = fileStorageContext.uploadFile(file, setting.getType());
        if (!uploadFile.getExist()) {
            MlogFiles mlogFiles = FileDTO.convert2Entity(uploadFile);
            this.save(mlogFiles);
        }
        return uploadFile;
    }
}
