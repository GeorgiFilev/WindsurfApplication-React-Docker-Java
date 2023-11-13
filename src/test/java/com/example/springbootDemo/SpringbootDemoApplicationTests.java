package com.example.springbootDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringbootDemoApplicationTests {


}
/*
package com.secretary.secretaryapp.controller;

        import com.openalpr.jni.LicenseReader;
        import com.secretary.secretaryapp.email.EmailService;
        import com.secretary.secretaryapp.model.Client;
        import com.secretary.secretaryapp.model.FileInfo;
        import com.secretary.secretaryapp.repository.ClientRepository;
        import com.secretary.secretaryapp.service.FilesStorageService;
        import org.apache.commons.io.IOUtils;
        import org.bytedeco.javacv.FrameFilter;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.core.io.Resource;
        import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
        import org.springframework.mock.web.MockMultipartFile;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.server.ServerErrorException;

        import javax.mail.Quota;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FileControllerTest {

    @MockBean
    private FilesStorageService storageService;

    @MockBean
    ClientRepository clientRepository;

    @MockBean
    private EmailService emailService;

    List<MultipartFile> files;

    @BeforeEach
    public void setUp(){
//        String[] clip = new String[1];
//        clip[0] = "D:\\git-repos\\S3-CB03-Group3\\PAAS\\src\\main\\java\\com\\video\\recognition\\eu-clip.mp4";
        // files.add(clip);
        files = new ArrayList<>();

        try{

            File file = new File("src/main/java/com/video/recognition/eu-clip.mp4");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", IOUtils.toByteArray(input));
            files.add(multipartFile);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void LoadFileFromPath(){
        MultipartFile file = files.get(0);
        String fileName = file.getOriginalFilename();
        assertEquals("eu-clip.mp4",fileName);

    }
    @Test
    void uploadFile() {

        storageService.save(files.get(0));
        String licensePlate = LicenseReader.licenseReader("uploads/"+ files.get(0).getOriginalFilename());
        Client client = clientRepository.findByLicensePlate(licensePlate);
        assertEquals("TF 77 FT",licensePlate);
    }

    @Test
    void getListFiles() {

    }

    @Test
    void getFile() {
    }
}
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {

    @MockBean
    private FilesStorageService storageService;

    @MockBean
   private ClientRepository clientRepository;

    @MockBean
    private EmailService emailService;

    List<MultipartFile> files;

    @BeforeEach
    public void setUp(){
//        String[] clip = new String[1];
//        clip[0] = "D:\\git-repos\\S3-CB03-Group3\\PAAS\\src\\main\\java\\com\\video\\recognition\\eu-clip.mp4";
        // files.add(clip);
        files = new ArrayList<>();
        storageService = mock(FilesStorageService.class);
        try{

            File file = new File("src/main/java/com/video/recognition/eu-clip.mp4");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", IOUtils.toByteArray(input));
            files.add(multipartFile);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void LoadFileFromPath(){
        MultipartFile file = files.get(0);
        String fileName = file.getOriginalFilename();
        assertEquals("eu-clip.mp4",fileName);

 */
