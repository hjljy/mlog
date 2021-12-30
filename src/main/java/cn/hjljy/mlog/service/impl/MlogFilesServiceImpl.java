package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.dto.FileDTO;
import cn.hjljy.mlog.entity.MlogFiles;
import cn.hjljy.mlog.filestorage.LocalFileStorage;
import cn.hjljy.mlog.mapper.MlogFilesMapper;
import cn.hjljy.mlog.service.IMlogFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-28
 */
@Service
public class MlogFilesServiceImpl extends ServiceImpl<MlogFilesMapper, MlogFiles> implements IMlogFilesService {

    @Autowired
    LocalFileStorage localFileStorage;
    @Override
    public FileDTO uploadFile(MultipartFile file) {
        FileDTO uploadFile = localFileStorage.uploadFile(file);
        if(!uploadFile.getExist()){
            MlogFiles mlogFiles = FileDTO.convert2Entity(uploadFile);
            this.save(mlogFiles);
        }
        return uploadFile;
    }
}
