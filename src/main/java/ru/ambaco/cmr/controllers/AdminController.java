package ru.ambaco.cmr.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ambaco.cmr.entities.User;
import ru.ambaco.cmr.service.AdminService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/")
public class AdminController {
    private final AdminService adminService;


    @GetMapping("/all")
    public List<User> getAll(){
        return adminService.getAll();
    }

    @GetMapping("/download")
    private ResponseEntity<InputStreamResource> download() throws IOException {
        String fileName = "liste_des_student.xlsx";
        ByteArrayInputStream inputStream = adminService.getDataDownloaded();
        InputStreamResource resource =  new InputStreamResource(inputStream);
        ResponseEntity<InputStreamResource> resourceResponseEntity = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" +fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(resource);
        return  resourceResponseEntity;
    }



}
