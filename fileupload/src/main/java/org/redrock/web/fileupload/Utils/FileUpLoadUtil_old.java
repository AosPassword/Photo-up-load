//package org.redrock.web.fileupload.Utils;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//public class FileUpLoadUtil_old {
//    public void getFile(HttpServletRequest request)
//            throws IOException, FileUploadException {
//        if(ServletFileUpload.isMultipartContent(request)){
//        //设置缓冲区的大小 以及设置临时文件的目录
//        DiskFileItemFactory disk =
//                new DiskFileItemFactory();
//        ServletFileUpload fileUpload=new ServletFileUpload(disk);
//        fileUpload.setSizeMax(1024*1024*10);
//        fileUpload.setHeaderEncoding("utf-8");
//        List<FileItem> list=fileUpload.parseRequest(request);
//            for (FileItem fileItem : list) {
//                if (fileItem.isFormField()) {
//                    String name = fileItem.getFieldName();// name属性值
//                    String value = fileItem.getString("utf-8");// name对应的value值
//                    System.out.println(name + " = " + value);
//                } else {
//                    String file_name = fileItem.getName();
//                    InputStream inputStream = fileItem.getInputStream();
//                    FileOutputStream fileOutputStream =
//                            new FileOutputStream("H:\\displayPicturePath\\" + file_name);
//                    byte[] buff = new byte[1024];
//                    int len = 0;
//                    while ((len = inputStream.read(buff)) > 0) {
//                        fileOutputStream.write(buff);
//                    }
//                    inputStream.close();
//                    fileOutputStream.close();
//                }
//            }
//        }else {
//            System.out.println("false!!!!!!!!!!!");
//        }
//    }
//}
