package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.HSSFUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;


@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList",userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public  Object saveCreateActivity(Activity activity, HttpSession session){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getName());

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);

            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    @ResponseBody
    public  Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate,
                                                    int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Activity> activityList=activityService.queryActivityByConditionForPage(map);
        int totalRows=activityService.queryCountOfActivityByCondition(map);
        //根据查询结果结果，生成响应信息
        Map<String,Object> retMap=new HashMap<>();
        retMap.put("activityList",activityList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    @ResponseBody
    public Object deleteActivityByIds(String[] ids){
        ReturnObject returnObject = new ReturnObject();

        try {
            int ret = activityService.deleteActivityByIds(ids);
            if(ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("删除失败~~~~!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("删除失败~~~~!");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Activity queryActivityById(String id){
        return activityService.queryActivityById(id);
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    @ResponseBody
    public Object saveEditActivity(Activity activity , HttpSession session){
        ReturnObject returnObject = new ReturnObject();

        //将修改者和修改时间加入activity
        activity.setEditTime(DateUtils.formateDateTime(new Date()));
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        activity.setEditBy(user.getId());

        try {
            int rows = activityService.saveEditActivity(activity);
            if(rows > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("修改失败~~~!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改失败~~~!");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/fileDownload.do") //测试下载文件
    public void fileDownload(HttpServletResponse response) throws Exception {         //使用流返回数据

        //1.设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //2.获取输出流
        OutputStream out = response.getOutputStream(); //字节流

        //设置响应头信息,使浏览器收到响应信息后,直接激活文件下载窗口
        //attachment:附件形式
        response.addHeader("Content-Disposition","attachment;filename=studentList.xls");

        //读取excel文件(inputStream),把文件输出到浏览器(outPutStream)
        FileInputStream fis = new FileInputStream("/home/lifu/activityList.xls");
        byte[] buff = new byte[256];
        int len = 0;
        while( (len = fis.read(buff)) != -1){
            out.write(buff,0,len);
        }

        fis.close();
        out.flush();//tomcat会关
    }


    @RequestMapping("/workbench/activity/exportAllActivitys.do")
    public void exportAllActivitys(HttpServletResponse response) throws Exception{
        List<Activity> activityList = activityService.queryAllActivitys();
        HSSFWorkbook wb = HSSFUtils.getActivityList(activityList); //使用apache.poi插件生成excel

//        FileOutputStream os = new FileOutputStream("/home/lifu/activityList.xls"); //写入磁盘，效率低
//        wb.write(os);
//        os.close();


        //把生成excel文件下载到客户端
        //1.设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8"); //告知浏览器这是一个字节流，浏览器处理字节流的默认方式就是下载。


        //2.获取输出流
        //读取excel文件(inputStream),把文件输出到浏览器(outPutStream)
        OutputStream out = response.getOutputStream(); //字节输出流


        //3.设置响应头信息,使浏览器收到响应信息后,直接激活文件下载窗口
        //attachment:附件形式
        response.addHeader("Content-Disposition","attachment;filename=AllActivityList.xls");


//        读磁盘,效率低
//        FileInputStream fis = new FileInputStream("/home/lifu/activityList.xls");
//        byte[] buff = new byte[256];
//        int len = 0;
//        while( (len = fis.read(buff)) != -1){
//            out.write(buff,0,len);
//        }
//
//        fis.close();
        wb.write(out); //内存到内存
        wb.close();
        out.flush();//tomcat会关
    }

    @RequestMapping("/workbench/activity/exportSelectActivities.do")
    public void exportXzActivity( String[] id , HttpServletResponse response) throws Exception{ //@RequestParam 暂时不加
        List<Activity> activityList = activityService.queryXzActivitys(id);
        HSSFWorkbook wb = HSSFUtils.getActivityList(activityList);//使用apache.poi插件生成excel
        OutputStream out = response.getOutputStream();

        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=XzActivityList.xls");

        wb.write(out);
        wb.close();
        out.flush();

    }


    //文件上传测试
    @RequestMapping("/workbench/activity/fileUpload.do")
    @ResponseBody
    public Object fileUpload(String userName, MultipartFile myFile) throws Exception {
        System.out.println("userName = " + userName);

        //获取文件名
        String originalFilename = myFile.getOriginalFilename();

        File file = new File("/home/lifu/firefox/" + originalFilename);
        myFile.transferTo(file);

        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("上传成功!!!");
        return returnObject;
    }

    @RequestMapping("/workbench/activity/importActivity.do")
    @ResponseBody
    public Object importActivity(MultipartFile activityFile , HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        try {
            //将excel文件写入磁盘文件
//            String originalFilename = activityFile.getOriginalFilename();
//            File file = new File("/home/lifu/firefox/" + originalFilename);
//            activityFile.transferTo(file);
//
//            InputStream is = new FileInputStream("/home/lifu/firefox/" + originalFilename);

            InputStream is = activityFile.getInputStream(); //由内存到内存
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;

            List<Activity> activityList = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                activity = new Activity();
                activity.setId(UUIDUtils.getUUID());//id需要唯一非空,不能由用户输入
                activity.setOwner(user.getId()); //使 当前登录用户 为导入的市场活动所有者
                activity.setCreateTime(DateUtils.formateDateTime(new Date()));
                activity.setCreateBy(user.getId());

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    String cellValue = HSSFUtils.getCellValueForStr(cell);
                    if (j == 0) { //规定excel的格式才能这样写
                        activity.setName(cellValue);
                    } else if (j == 1) {
                        activity.setStartDate(cellValue);
                    } else if (j == 2) {
                        activity.setEndDate(cellValue);
                    } else if (j == 3) {
                        activity.setCost(cellValue);
                    } else if (j == 4) {
                        activity.setDescription(cellValue);
                    }
                }
                activityList.add(activity); //每一行就是一个activity对象
            }

            int ret = activityService.saveCreateActivityByList(activityList);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(ret); //用于前端显示导入了几条信息
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙...");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id , HttpServletRequest request){
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);

        request.setAttribute("activity", activity);
        request.setAttribute("remarkList" , remarkList);

        return "workbench/activity/detail";
    }
}
