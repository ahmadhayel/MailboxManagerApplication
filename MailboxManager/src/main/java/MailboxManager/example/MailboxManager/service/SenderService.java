package MailboxManager.example.MailboxManager.service;

import MailboxManager.example.MailboxManager.Entity.Sender;
import MailboxManager.example.MailboxManager.repo.SenderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SenderService {

    @Autowired
    private SenderRepository senderRepository;



    public Sender createSender(@RequestBody Sender sender) {
        return senderRepository.save(sender);
    }


    public List<Sender> getAllSenders() {
        return senderRepository.findAll();
    }

    public Optional<Sender> getSenderById(Long id) {
        return senderRepository.findById(id);
    }

    public Sender updateSender(Long id, String name) {
        Optional<Sender> optionalSender = senderRepository.findById(id);
        if (optionalSender.isPresent()) {
            Sender sender = optionalSender.get();
            sender.setName(name);
            return senderRepository.save(sender);
        } else {
            throw new RuntimeException("Sender not found with id: " + id);
        }
    }

    public void deleteSender(Long id) {
        Optional<Sender> senderOptional = senderRepository.findById(id);
        if (senderOptional.isPresent()) {
            senderRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Avsändaren med ID " + id + " finns inte i databasen.");
        }
    }
}
