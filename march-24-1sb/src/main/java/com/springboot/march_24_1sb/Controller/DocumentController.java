package com.springboot.march_24_1sb.Controller;


import com.springboot.march_24_1sb.Service.DocumentService;
import com.springboot.march_24_1sb.model.Document;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/document")
@CrossOrigin("http://localhost:5173/")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public Document uploadFile(Principal principal,
                               @RequestParam("file")MultipartFile file) throws IOException {

        return documentService.upload(principal.getName(),file);


    }


}
