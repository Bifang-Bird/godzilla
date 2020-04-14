package com.simaba.gateway.drive.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ：ywq
 * @date ：Created in 2019/11/7 11:14
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static void createFile(String strPath) {
        //文件夹
        try {
            File file = new File(strPath);
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param targetPath 目的压缩文件保存路径
     * @return void
     * @throws Exception
     * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip
     */
    public static void compressedFile(String resourcesPath, String targetPath) throws Exception {
        File resourcesFile = new File(resourcesPath);     //源文件
        File targetFile = new File(targetPath);           //目的文件夹
        //如果目的路径不存在，则新建
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String targetName ="" ;
        if(resourcesFile.getName().indexOf(".")!=-1){
            targetName = resourcesFile.getName().substring(0,resourcesFile.getName().indexOf("."))+".zip";      //如果是文件则取文件名字
        }else{
            targetName = resourcesFile.getName() + ".zip";   //目的压缩文件名
        }
        FileOutputStream outputStream = new FileOutputStream(targetPath + "\\" + targetName);
        CheckedOutputStream cos = new CheckedOutputStream(outputStream, new CRC32());
        ZipOutputStream out = new ZipOutputStream(cos);
        createCompressedFile(out, resourcesFile, "");
        out.close();
        deleteFile(resourcesPath);
    }

    /**
     * @param out  输出流
     * @param file 目标文件
     * @return void
     * @throws Exception
     * @desc 生成压缩文件。
     * 如果是文件夹，则使用递归，进行文件遍历、压缩
     * 如果是文件，直接压缩
     */
    public static void createCompressedFile(ZipOutputStream out, File file, String dir) throws Exception {
        //如果当前的是文件夹，则进行进一步处理
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));

            dir = dir.length() == 0 ? "" : dir + "/";

            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                createCompressedFile(out, files[i], dir + files[i].getName());         //递归处理
            }
        } else {   //当前的是文件，打包处理
            //文件输入流
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(dir + file.getName());
            out.putNextEntry(entry);
            // out.putNextEntry(new ZipEntry(dir));
            //进行写操作
            int j = 0;
            byte[] buffer = new byte[1024];
            while ((j = bis.read(buffer)) > 0) {
                out.write(buffer, 0, j);
            }
            //关闭输入流
            bis.close();
        }
    }


    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                LOGGER.info("删除文件" + fileName + "成功！");
                return true;
            } else {
                LOGGER.info("删除文件" + fileName + "失败！");
                return false;
            }
        } else {
            LOGGER.info("删除文件失败" + fileName + "不存在！");
            return false;
        }
    }
}
