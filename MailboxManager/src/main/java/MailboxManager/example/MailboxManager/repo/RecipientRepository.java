package MailboxManager.example.MailboxManager.repo;

import MailboxManager.example.MailboxManager.Entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
    List<Recipient> findAllByIdIn(List<Long> recipientIds);

   // List<Recipient> findById(List<Long> recipientId);
    // Lägg till eventuella anpassade metoder för att hämta mottagare om det behövs
    //Optional<Recipient> findById(Long id);
}
