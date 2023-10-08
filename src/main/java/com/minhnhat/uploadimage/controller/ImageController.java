package com.minhnhat.uploadimage.controller;

import com.minhnhat.uploadimage.entity.Image;
import com.minhnhat.uploadimage.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private HttpServletRequest request;
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";
    @PostMapping
    public String uploadImage(@RequestParam("image") MultipartFile photo) {
        if(photo.isEmpty()){
            return "no image";
        }
        Path path = Paths.get(uploadDirectory);
        try{
            InputStream inputStream = photo.getInputStream();
            Files.copy(inputStream,path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.getMessage();
        }
        return "redirect:/" + request.getServerName();
    }
}
