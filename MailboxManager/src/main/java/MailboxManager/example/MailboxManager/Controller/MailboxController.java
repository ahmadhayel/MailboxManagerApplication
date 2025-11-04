package MailboxManager.example.MailboxManager.Controller;


import MailboxManager.example.MailboxManager.Entity.Files;
import MailboxManager.example.MailboxManager.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
public class MailboxController {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private MailboxManager.example.MailboxManager.service.FilesService filesService;



   @PostMapping("/uploadFilesIntoDB")
    public ResponseEntity<String> storeFilesIntoDB(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("senderIds") List<Long> senderIds,
                                                   @RequestParam("recipientIds") List<Long> recipientIds) {
        String response = filesService.storeFileIntoDB(file, senderIds, recipientIds);
        if (response.startsWith("Failed")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/messages")
    public List<Files> getMessages() {
        return fileRepository.findAll();
    }

   @DeleteMapping("messages/{id}")
   public ResponseEntity<Void> deleteFiles(@PathVariable("id") Long id) throws FileNotFoundException {
       filesService.deleteFiles(id);
       return ResponseEntity.noContent().build();
   }



    @PostMapping("/uploadFilesIntoDBForAllRecipients")
    public ResponseEntity<String> storeFilesIntoDBForAllRecipients(@RequestParam("file") MultipartFile file,
                                                                   @RequestParam("senderIds") List<Long> senderIds) {
        String response = filesService.storeFileToAllRecipients(file, senderIds);
        if (response.startsWith("Failed")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
