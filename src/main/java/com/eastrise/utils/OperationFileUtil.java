package com.eastrise.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * crete by FQY on 2018/11/23
 */

public class OperationFileUtil {

    private static final String encoding = "utf-8";

    public static void uploadFile(MultipartFile file, String filePath, String fileName) throws Exception {
        if(!file.isEmpty()){
            File dirFile = new File(filePath);
            if (!dirFile.exists()) {
                Boolean flg=dirFile.mkdirs();
            }
            InputStream in = file.getInputStream();
            FileOutputStream os = new FileOutputStream(filePath+ "/" + fileName);
            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = in.read(buffer)) > 0) {
                os.write(buffer, 0, read);
            }
            os.close();
            in.close();
        }else{
            throw new Exception("文件必须存在！！");
        }
    }


    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
