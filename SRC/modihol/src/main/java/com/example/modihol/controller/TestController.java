package com.example.modihol.controller;

import javax.sql.DataSource;
import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {
    @Autowired
    private DataSource datasource;

    public TestController(DataSource datasource) {
        this.datasource = datasource;
    }
    @GetMapping("/test")
    public String testDataConnect(){
        try (Connection connection = datasource.getConnection()){
            if(connection != null){
                return "ket noi thanh cong" + connection.getMetaData().getDatabaseProductName();
            }
        }catch(Exception e){
            return "ket noi that bai" + e.getMessage();
        }
        return "Không thể thiết lập kết nối";
    }
   
    
}
