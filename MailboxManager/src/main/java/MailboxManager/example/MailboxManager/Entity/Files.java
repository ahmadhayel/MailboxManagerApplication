package MailboxManager.example.MailboxManager.Entity;

import lombok.Builder;

import jakarta.persistence.*;
import lombok.*;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "files")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String name;
    private String type;
    private String path;

    @Lob
    private byte[] imageData;
    @ManyToOne
    @JoinColumn(name = "recipient_ids")
    private Recipient recipient;



  /*  @ManyToOne
    @JoinColumn(name = "sender_ids")
    private Sender sender;





@ManyToMany
    @JoinTable(
            name = "file_sender_recipientss",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id", referencedColumnName = "id"))

   @Singular ("recipient")
   private List<Recipient> recipients;*/

 @ManyToMany
    @JoinTable(
            name = "file_sender_recipient",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id")
    )
    @Singular ("sender")
    private List<Sender> sender;

    // getters and setters

    @OneToMany(mappedBy = "files")
    private List<Recipient> recipients;

    // Getter och setter för recipients

    public void addRecipient(Recipient recipient) {
        if (recipients == null) {
            recipients = new ArrayList<>();
        }
        recipients.add(recipient);
        recipient.setFiles(this);
    }

}


