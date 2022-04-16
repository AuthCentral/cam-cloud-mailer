package tech.mayanksoni.cam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.mayanksoni.cam.domains.MailingHistory;

public interface MailingHistoryRepository extends MongoRepository<MailingHistory, String> {

}
