package MailboxManager.example.MailboxManager.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import MailboxManager.example.MailboxManager.Entity.Files;
import MailboxManager.example.MailboxManager.Entity.Recipient;
import MailboxManager.example.MailboxManager.Entity.Sender;
import MailboxManager.example.MailboxManager.repo.FileRepository;
import MailboxManager.example.MailboxManager.repo.RecipientRepository;
import MailboxManager.example.MailboxManager.repo.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Service
public class FilesService {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private SenderRepository senderRepository;



    //skicka till alla mottagre (klar)
  public String storeFileToAllRecipients(MultipartFile file, List<Long> senderIds) {
      try {
          // Hämta avsändaren från databasen om den redan finns
          Optional<Sender> optionalSender = senderIds.stream()
                  .map(senderRepository::findById)
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .findFirst();

          if (optionalSender.isPresent()) {
              Sender sender = optionalSender.get();

              // Hämta mottagarna från databasen baserat
              List<Recipient> recipients = recipientRepository.findAll();

              // Skapa filobjektet och länka till avsändaren
              Files files = Files.builder()
                      .sender(sender)
                      .name(file.getOriginalFilename())
                      .type(file.getContentType())
                      .imageData(file.getBytes())
                      .build();

              // Lägg till mottagare till filobjektet
              for (Recipient recipient : recipients) {
                  files.addRecipient(recipient);
              }

              // Spara filen med kopplingen till avsändaren och mottagarna i databasen
              fileRepository.save(files);

              return "File uploaded successfully to all recipients";
          } else {
              return "Failed to upload file: Sender not found in the database";
          }
      } catch (IOException e) {
          return "Failed to upload file to all recipients: " + e.getMessage();
      }
  }



    //Tbaort file (klar)
    public void deleteFiles(Long id) throws FileNotFoundException {
        Optional<Files> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            fileRepository.deleteById(id);
        } else {
            throw new FileNotFoundException("Filen med ID " + id + " finns inte i databasen.");
        }
    }

    //skikca till motgare vid Id (klar)
    public String storeFileIntoDB(MultipartFile file, List<Long> senderIds, List<Long> recipientIds) {
        try {
            // Hämta avsändaren från databasen om den redan finns
            Optional<Sender> optionalSender = senderIds.stream()
                    .map(senderRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();

            if (optionalSender.isPresent()) {
                Sender sender = optionalSender.get();

                // Hämta mottagarna från databasen baserat på deras ID:n
                List<Recipient> recipients = recipientRepository.findAllByIdIn(recipientIds);

                // Skapa filobjektet och länka till avsändaren
                Files files = Files.builder()
                        .sender(sender)
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(file.getBytes())
                        .build();

                // Lägg till mottagare till filobjektet
                for (Recipient recipient : recipients) {
                    files.addRecipient(recipient);
                }

                // Spara filen med kopplingen till avsändaren och mottagarna i databasen
                fileRepository.save(files);

                return "File uploaded successfully to all recipients";
            } else {
                return "Failed to upload file: Sender not found in the database";
            }
        } catch (IOException e) {
            return "Failed to upload file to all recipients: " + e.getMessage();
        }
    }
}