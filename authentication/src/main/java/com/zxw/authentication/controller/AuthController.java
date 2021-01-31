//package com.zxw.authentication.controller;
//
//import com.alibaba.excel.util.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * @author zxw
// */
//@RequestMapping("/auth")
//@Controller
//@Slf4j
//public class AuthController {
//
//    @RequestMapping("/index")
//    public String index() {
//        return "index";
//    }
//
//    @RequestMapping("/login")
//    public String login(String username, String password) {
//        if (StringUtils.isEmpty(username)) {
//            log.info("跳转到登录页面");
//            return "login";
//        }
//        try {
//            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
//        } catch (UnknownAccountException | IncorrectCredentialsException e) {
//            e.printStackTrace();
//            //这个login是指页面/static/login.html
//            return "login";
//        }
//        return "index";
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        SecurityUtils.getSubject().logout();
//        return "login";
//    }
//
//    @GetMapping("/test")
//    @ResponseBody
//    @RequiresRoles("admin")
//    public String test() {
//        return "success";
//    }
//
//    public static void main(String[] args) {
////        List<Map<Integer,String>> objects1 = EasyExcelFactory.read("D:" + File.separator + "餐.xlsx")
////                .sheet(0)
////                .headRowNumber(0)
////                .doReadSync();
////        List<ExcelData> objects = EasyExcelFactory.read("D:" + File.separator + "餐.xlsx")
////                .sheet(0)
////                .head(ExcelData.class)
////                .doReadSync();
////        System.out.println(1);
////        EasyExcel.write("D:" + File.separator + "测试.xlsx").sheet(0).head(Arrays.asList("临")).doWrite(objects);
//    }
//
//}
