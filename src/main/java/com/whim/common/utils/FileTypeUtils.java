package com.whim.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

/**
 * @author Jince
 * @since: 2023.12.15 下午 08:45
 * @description: 文件类型工具类
 */
public class FileTypeUtils {
    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_BMP = "image/bmp";

    public static final String IMAGE_GIF = "image/gif";

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // 视频格式
            "mp4", "avi", "rmvb",
            // pdf
            "pdf"};

    /**
     * 获取文件类型
     * <p>
     * 例如: ruoyi.txt, 返回: txt
     *
     * @param file 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(File file) {
        if (null == file) {
            return StringUtils.EMPTY;
        }
        return getFileType(file.getName());
    }

    /**
     * 获取文件类型
     * <p>
     * 例如: ruoyi.txt, 返回: txt
     *
     * @param fileName 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(String fileName) {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0) {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }

    /**
     * 获取文件类型
     *
     * @param photoByte 文件字节码
     * @return 后缀（不含".")
     */
    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = "JPG";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97)) {
            strFileExtendName = "GIF";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70)) {
            strFileExtendName = "JPG";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            strFileExtendName = "BMP";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            strFileExtendName = "PNG";
        }
        return strFileExtendName;
    }

    public static String getExtension(String prefix) {
        return switch (prefix) {
            case IMAGE_PNG -> "png";
            case IMAGE_JPG -> "jpg";
            case IMAGE_JPEG -> "jpeg";
            case IMAGE_BMP -> "bmp";
            case IMAGE_GIF -> "gif";
            default -> "";
        };
    }
}
