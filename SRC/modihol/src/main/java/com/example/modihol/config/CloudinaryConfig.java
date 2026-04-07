package com.example.modihol.config;
import com.cloudinary.Cloudinary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class CloudinaryConfig {
   @Bean
   public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap();
        config.put("cloud_name","dj8r9cpjw");
        config.put("api_key","364891856742943");
        config.put("api_secret","2Jmu3tiLUEm_PqHp7sdZ68QKUmI");
        config.put("secure","true");
        return new Cloudinary(config);

        
   } 
}
