package MailboxManager.example.MailboxManager.repo;
import MailboxManager.example.MailboxManager.Entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FileRepository extends JpaRepository<Files, Long> {

    Files findByName(String name);
}