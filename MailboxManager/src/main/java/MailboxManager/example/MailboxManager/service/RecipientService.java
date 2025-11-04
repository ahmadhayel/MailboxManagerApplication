package MailboxManager.example.MailboxManager.service;

import MailboxManager.example.MailboxManager.Entity.Recipient;
import MailboxManager.example.MailboxManager.Entity.Sender;
import MailboxManager.example.MailboxManager.repo.RecipientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;

    public Recipient createRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }


    public List<Recipient> getAllRecipients() {
        return recipientRepository.findAll();
    }

    public Optional<Recipient> getRecipientById(Long id) {
        return recipientRepository.findById(id);
    }


    public Recipient updateRecipient(Long id, String name) {
        Optional<Recipient> optionalRecipient = recipientRepository.findById(id);
        if (optionalRecipient.isPresent()) {
            Recipient recipient = optionalRecipient.get();
            recipient.setName(name);
            return recipientRepository.save(recipient);
        } else {
            throw new RuntimeException("Sender not found with id: " + id);
        }
    }



    public void deleteRecipient(Long id) {
        Optional<Recipient> recipientOptional = recipientRepository.findById(id);
        if (recipientOptional.isPresent()) {
            recipientRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Mottagaren med ID " + id + " finns inte i databasen.");
        }
    }

}
