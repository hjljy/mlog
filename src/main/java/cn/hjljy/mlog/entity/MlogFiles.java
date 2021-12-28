package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mlog_files")
public class MlogFiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 全路径
     */
    @TableField("full_path")
    private String fullPath;

    /**
     * 存储方式：本地 云存储
     */
    @TableField("storage")
    private String storage;

    /**
     * 上传时间
     */
    @TableField("create_time")
    private Long createTime;


}
