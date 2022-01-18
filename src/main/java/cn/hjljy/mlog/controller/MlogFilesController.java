package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.support.ResultInfo;
import cn.hjljy.mlog.model.dto.FileDTO;
import cn.hjljy.mlog.service.IMlogFilesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-28
 */
@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class MlogFilesController {

    private final IMlogFilesService filesService;

    @PostMapping("upload")
    public ResultInfo<FileDTO> upload(MultipartFile file){
        FileDTO files= filesService.uploadFile(file);
        return ResultInfo.success(files);
    };
}

