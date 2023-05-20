package zootycoon.project.zoowebsite.account;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, ObjectId> {

    Optional<Account> findAccountByusername(String username);
}
