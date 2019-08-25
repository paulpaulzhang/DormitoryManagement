package cn.paulpaulzhang.dormitorymanagement.file;

import java.util.List;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.util.file
 * 创建时间: 7/31/2019
 * 创建人: zlm31
 * 描述:
 */
public interface IUploadFileListener {
    void onSuccess(String path);
    void onError();
}
