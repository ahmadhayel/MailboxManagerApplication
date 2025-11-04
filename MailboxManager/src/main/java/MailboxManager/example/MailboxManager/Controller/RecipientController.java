package MailboxManager.example.MailboxManager.Controller;

import MailboxManager.example.MailboxManager.Entity.Recipient;
import MailboxManager.example.MailboxManager.Entity.Sender;
import MailboxManager.example.MailboxManager.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipients")
public class RecipientController {

    @Autowired
    private RecipientService recipientService;

    @PostMapping ("/create")
    public ResponseEntity<Recipient> createRecipient(@RequestBody Recipient recipient) {
        Recipient createdRecipient = recipientService.createRecipient(recipient);
        return ResponseEntity.ok(createdRecipient);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipient>> getAllRecipients() {
        List<Recipient> recipients = recipientService.getAllRecipients();
        return ResponseEntity.ok(recipients);
    }


       @GetMapping("/{id}")
    public ResponseEntity<Recipient> getRecipientById(@PathVariable("id") Long id) {
        Optional<Recipient> recipient = recipientService.getRecipientById(id);
        if (recipient.isPresent()) {
            return ResponseEntity.ok(recipient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipient> updateRecipient(@PathVariable("id") Long id, @RequestParam("name") String name) {
        Recipient updatedRecipient = recipientService.updateRecipient(id, name);
        return ResponseEntity.ok(updatedRecipient);
    }

    // Endpoint för att ta bort en avsändare baserat på ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipient(@PathVariable("id") Long id) {
        recipientService.deleteRecipient(id);
        return ResponseEntity.noContent().build();
    }

}


