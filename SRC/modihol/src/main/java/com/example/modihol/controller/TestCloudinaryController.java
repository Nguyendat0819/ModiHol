// package com.example.modihol.controller;
// import com.example.modihol.config.CloudinaryConfig;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.Map;
// import com.cloudinary.utils.ObjectUtils;
// import com.cloudinary.Cloudinary;
// @RestController
// public class TestCloudinaryController {
//     @Autowired
//     private Cloudinary cloudinary;

//     @PostMapping("/test-cloudinary")
//     public String test(@RequestParam("file") MultipartFile file) {
//         try{
//             System.out.println(cloudinary.config.cloudName);
//             Map result = cloudinary.uploader().upload(
//                 file.getBytes(),
//                 ObjectUtils.asMap("folder","Modihol")
//             );
//             System.out.println(result);
//             return result.get("secure_url").toString();
//         }catch(Exception e){
//             e.printStackTrace();
//             return "FAILED" + e.getMessage();
//         }
   
//     }

// }

// Test thành công kết nốt và đư được vào folders
