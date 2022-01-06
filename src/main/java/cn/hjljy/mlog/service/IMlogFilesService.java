package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.dto.FileDTO;
import cn.hjljy.mlog.model.entity.MlogFiles;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-28
 */
public interface IMlogFilesService extends IService<MlogFiles> {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link FileDTO}
     */
    FileDTO uploadFile(MultipartFile file);
}
