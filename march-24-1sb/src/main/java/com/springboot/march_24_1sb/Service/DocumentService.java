package com.springboot.march_24_1sb.Service;


import com.springboot.march_24_1sb.Repository.DocumentRepository;
import com.springboot.march_24_1sb.model.Customer;
import com.springboot.march_24_1sb.model.Document;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final CustomerService customerService;

    private final static String UPLOAD_PATH="/Users/dhakshnamoorthy/Desktop/ReactTrs/trs-ui/src/assets/uploads";

    public Document upload(String name, MultipartFile file) throws IOException {

        Customer customer = customerService.getCustomerByUserName(name);

        File file1 = new File(UPLOAD_PATH);

        String fileName = file.getOriginalFilename();

        Path path = Paths.get(UPLOAD_PATH+"/"+fileName);

        Files.write(path,file.getBytes());

        Document document = new Document();
        document.setDocumentNamee(fileName);
        document.setCustomer(customer);

        return documentRepository.save(document);
    }
}
