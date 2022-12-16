package kz.zhanbolat.data.resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {

    // TODO: move to separate validation class
    private static List<String> IMAGE_FILE_EXTENSION = List.of("jpg", "png");

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile image) {
        // receive an image from user
        Resource imageResource = image.getResource();
        String fileExtension = FilenameUtils.getExtension(imageResource.getFilename());
        if (!IMAGE_FILE_EXTENSION.contains(fileExtension)) {
            return ResponseEntity.badRequest().body("{ \"error\": { \"message\": \"Input file is not an image\"} }");
        }
        log.info("Image name: {}", image.getName());
        return ResponseEntity.accepted().build();
    }
}
