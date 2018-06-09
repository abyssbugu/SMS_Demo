package com.abyss.view;

import com.abyss.pojo.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Abyss on 2018/5/31.
 * description:
 */
public class UserExcelView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //从model对象中获取数据,key必须和exportExcel中的model对象中的key一致。
        List<User> userList = (List<User>) model.get("userList");

        //创建sheet
        Sheet sheet = workbook.createSheet("会员列表");
        //创建表的标题
        Row header = sheet.createRow(0);
        //在第一行中创建单元格
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("用户名");
        header.createCell(2).setCellValue("姓名");
        header.createCell(3).setCellValue("年龄");
        header.createCell(4).setCellValue("性别");
        header.createCell(5).setCellValue("出生日期");
        header.createCell(6).setCellValue("创建日期");
        header.createCell(7).setCellValue("更新日期");

        if (userList != null && userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUserName());
                row.createCell(2).setCellValue(user.getName());
                row.createCell(3).setCellValue(user.getAge());
                //从对象中获取的性别是1和0，需要在表中显示男和女
                String sex;
                if (user.getSex() == 1) {
                    sex = "男";
                } else if (user.getSex() == 2) {
                    sex = "女";
                } else {
                    sex = "未知";
                }
                row.createCell(4).setCellValue(sex);
//                row.createCell(5).setCellValue(user.getBirthday());
//                row.createCell(6).setCellValue(user.getCreated());
//                row.createCell(7).setCellValue(user.getUpdated());
                row.createCell(5).setCellValue(new DateTime(user.getBirthday()).toString("yyyy-MM-dd"));
                row.createCell(6).setCellValue(new DateTime(user.getCreated()).toString("yyyy-MM-dd hh:mm:ss"));
                row.createCell(7).setCellValue(new DateTime(user.getUpdated()).toString("yyyy-MM-dd hh:mm:ss"));
            }
        }
        // 设置相应头信息，以附件形式下载并且指定文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("会员列表.xls".getBytes(),"ISO-8859-1"));

    }
}
