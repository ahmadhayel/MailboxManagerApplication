package MailboxManager.example.MailboxManager.Controller;


import MailboxManager.example.MailboxManager.Entity.Sender;
import MailboxManager.example.MailboxManager.repo.SenderRepository;
import MailboxManager.example.MailboxManager.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/senders")
public class SenderController {

    @Autowired
    private SenderService senderService;
    @Autowired
    private SenderRepository senderRepository;

    @PostMapping("/create")
    public ResponseEntity<Sender> createSender(@RequestBody Sender sender) {
        Sender createdSender = senderService.createSender(sender);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSender);
    }

    // Endpoint för att hämta alla avsändare
    @GetMapping("/all")
    public ResponseEntity<List<Sender>> getAllSenders() {
        List<Sender> senders = senderService.getAllSenders();
        return ResponseEntity.ok(senders);
    }

    // Endpoint för att hämta en specifik avsändare baserat på ID
    @GetMapping("/{id}")
    public ResponseEntity<Sender> getSenderById(@PathVariable("id") Long id) {
        Optional<Sender> sender = senderService.getSenderById(id);
        if (sender.isPresent()) {
            return ResponseEntity.ok(sender.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint för att uppdatera en avsändare baserat på ID
    @PutMapping("/{id}")
    public ResponseEntity<Sender> updateSender(@PathVariable("id") Long id, @RequestParam("name") String name) {
        Sender updatedSender = senderService.updateSender(id, name);
        return ResponseEntity.ok(updatedSender);
    }

    // Endpoint för att ta bort en avsändare baserat på ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSender(@PathVariable("id") Long id) {
        senderService.deleteSender(id);
        return ResponseEntity.noContent().build();
    }

}
