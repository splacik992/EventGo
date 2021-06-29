package com.pali.eventgo.utils;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageUploader {

    private final Cloudinary cloudinary;
    private final static Map<Object, Object> CONFIG = new HashMap<>();

    static {
        CONFIG.put("cloud_name", "dxsbinn53");
        CONFIG.put("api_key", "364697739851231");
        CONFIG.put("api_secret", "ME-wLBFyDeUSGb_d5ihXbF3thHA");
    }


    public ImageUploader() {
        cloudinary = new Cloudinary(CONFIG);
    }


    public String uploadFile(String filePath) {
        File file = new File(filePath);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();

        }
        return uploadResult.get("url").toString();
    }


}
