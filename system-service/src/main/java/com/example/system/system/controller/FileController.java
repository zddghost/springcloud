package com.example.system.system.controller;

import com.common.core.basebean.ResponseBean;
import com.common.core.basecontroller.BaseController;
import com.common.core.utils.ExportExcelUtil;
import com.common.core.utils.ImportExcelUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("file")
public class FileController extends BaseController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Value("${data.files}")
    private String files;

    @Value("${data.images}")
    private String images;

    /*文件上传*/
    @PostMapping("upload")
    public ResponseBean upload(MultipartFile file) {
        if (StringUtils.isEmpty(file)) {
            return failure("请上传文件!");
        }
        long size = file.getSize();

        String newfileName = null;
        try {
            newfileName = getNewfileName(newfileName, file);
            String filepath = getFilePath(newfileName);
            File newFile = new File(filepath);
            FileUtils.writeByteArrayToFile(newFile, file.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage());
            return failure("文件上传出错!");
        }
        return success(newfileName);
    }

    private String getFilePath(String newfileName) {
        String filepath = null;
        String[] str = {"png", "jpg", "jpeg"};
        List<String> imageNemas = Arrays.asList(str);
        int i = 1;
        for (String imageNema : imageNemas) {
            boolean contains = newfileName.contains(imageNema);
            if (contains) {
                i = 2;
            }
        }
        switch (i) {
            case 1:
                filepath = files + newfileName;
                break;
            case 2:
                filepath = images + newfileName;
                break;
            default:
                break;
        }

        return filepath;
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param response
     */
    @GetMapping("download")
    public void download(String fileName, HttpServletResponse response) {
        String filePath = getFilePath(fileName);
        File file = new File(filePath);
        fileName = file.getName();
        try {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/force-download");// 设置强制下载不打开
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            byte[] bytes = FileUtils.readFileToByteArray(file);
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("文件下载出错!");
        }
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param response
     */
    @GetMapping("image")
    public ResponseBean image(String fileName, HttpServletResponse response) {
        String filepath = images + fileName;
        File file = new File(filepath);
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            String s = "文件不存在!";
            log.error(s);
            return failure(s);
        }
        return success();
    }

    private String getNewfileName(String newfileName, MultipartFile file) {
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        String yyyyMMdd = sdf.format(new Date());
        Long l = System.currentTimeMillis();
        newfileName = yyyyMMdd + "/" + l + "." + suffix;
        return newfileName;
    }

    /**
     * 导出商品备案信息
     *
     * @param
     * @return
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception {

        //查询数据，实际可通过传过来的参数当条件去数据库查询，在此我就用空集合（数据）来替代
        /*List<MenusTree> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MenusTree menusTree = new MenusTree();
            menusTree.setId(i + "");
            menusTree.setName("zhaoxixi");
            menusTree.setUrl("www.baidu.com");
            menusTree.setIcon("cccc:xcxx");
            menusTree.setFlag(true);
            list.add(menusTree);
        }*/
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 1; i <= 1047001; i++) {
            Map<String, String> titles = new LinkedHashMap<>();
            titles.put("id", "主键" + i);
            titles.put("name", "名称" + i);
            titles.put("url", "路径" + i);
            titles.put("icon", "图标" + i);
            titles.put("flag", "标识" + i);
            list.add(titles);
        }
        Map<String, String> titles = new LinkedHashMap<>();
        titles.put("主键-10", "id");
        titles.put("名称-10", "name");
        titles.put("路径-10", "url");
        titles.put("图标-10", "icon");
//        titles.put("标识-10", "flag");
        String sheetname = "测试";
        ExportExcelUtil.outfile(response, titles, sheetname, list);
    }

    /**
     * 描述：通过传统方式form表单提交方式导入excel文件
     *
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "importExcel")
    public ResponseBean importExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upfile");
        if (file.isEmpty()) {
            throw new Exception("文件不存在！");
        }
        List<List<Object>> listob=new ArrayList<>();
        try {
            // 对读取Excel表格标题测试
            InputStream in = file.getInputStream();
            listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
            in.close();
            //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
            if (!StringUtils.isEmpty(listob)) {
                for (int i = 0; i < listob.size(); i++) {
                    List<Object> lo = listob.get(i);
                    System.out.println(lo.get(0) + " ," + lo.get(1) + "," + lo.get(2));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return failure(e.getMessage());
        }
        return success(listob);
    }


}
