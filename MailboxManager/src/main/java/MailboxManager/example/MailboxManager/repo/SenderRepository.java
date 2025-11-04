package MailboxManager.example.MailboxManager.repo;

import MailboxManager.example.MailboxManager.Entity.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {
    <T> Optional<T> findByName(String senderName);

    Optional<Sender> findAllByIdIn(List<Long> senderIds);

   // Optional<Sender> findById(List<Long> senderId);
    // Här kan du lägga till eventuella anpassade metoder för hämtning av avsändare om det behövs
}
