package com.hxq.mobile.support.service;

import org.springframework.web.multipart.MultipartFile;

import com.hxq.mobile.entity.common.ImgUploadResponse;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.repository.SimpleEntityService;

public interface ImgUploadService extends SimpleEntityService {

    /**
     * 保存上传图片
     * @param thumbnail 是否生成缩略图
     * @param files 上传文件数组
     * @return 上传文件保存信息
     * @throws Exception 
     */
    public ImgUploadResponse updateForUpload(boolean thumbnail, MultipartFile[] files) throws Exception;

    /**
     * 将上传图片绑定至业务表记录
     * @param tbName 业务表名
     * @param pkName 业务记录主键，多主键通过逗号连接
     * @param ids 已上传的图片id
     * @return
     */
    public boolean updateForBindingBusiness(String tbName, String pkValue, String... ids);

    /**
     * 通过业务表名和业务主键获取业务相关图片url
     * @param tbName 业务表名
     * @param pkValue 业务主键
     * @return
     */
    public String[] selectImagesByTableName(String tbName, String pkValue);

    /**
     * 通过图片id获取图片url
     * @param ids
     * @return
     */
    public Image4App[] selectImagesByIds(String... ids);
}
