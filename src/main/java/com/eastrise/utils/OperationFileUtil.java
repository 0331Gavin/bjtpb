package com.eastrise.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
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
    public static void prototypeDownload(File file, String returnName, HttpServletResponse response){
        // 下载文件
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            if(!file.exists()) return;
            response.reset();
            //设置响应类型	PDF文件为"application/pdf"，WORD文件为："application/msword"， EXCEL文件为："application/vnd.ms-excel"。
            response.setContentType("application/octet-stream;charset=utf-8");

            //设置响应的文件名称,并转换成中文编码
            //returnName = URLEncoder.encode(returnName,"UTF-8");
            returnName = response.encodeURL(new String(returnName.getBytes(),"iso8859-1"));	//保存的文件名,必须和页面编码一致,否则乱码

            //attachment作为附件下载；inline客户端机器有安装匹配程序，则直接打开；注意改变配置，清除缓存，否则可能不能看到效果
            response.addHeader("Content-Disposition",   "attachment;filename="+returnName);

            //将文件读入响应流
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            int length = 1024;
            int readLength=0;
            byte buf[] = new byte[1024];
            readLength = inputStream.read(buf, 0, length);
            while (readLength != -1) {
                outputStream.write(buf, 0, readLength);
                readLength = inputStream.read(buf, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
