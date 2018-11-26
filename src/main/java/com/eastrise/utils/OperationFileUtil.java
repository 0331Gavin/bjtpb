package com.eastrise.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

    /**
     *
     * @param response
     * @param fileName  下载文件名称
     * @param filePath  下载文件路径（斜杠请用反斜杠“/”）
     * @return
     */
    public String downloadFile(HttpServletResponse response,String fileName,String filePath){
        if (fileName != null) {
            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName=" +  fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
