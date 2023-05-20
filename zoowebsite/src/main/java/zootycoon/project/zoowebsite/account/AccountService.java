package zootycoon.project.zoowebsite.account;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zootycoon.project.zoowebsite.animal.Animal;
import zootycoon.project.zoowebsite.zoo.Zoo;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public List<Account> fetchAllAccounts(){
        return accountRepository.findAll();
    }

    public Optional<Account> fetchSingleAccount(ObjectId id) {
        return accountRepository.findById(id);
    }
    public Optional<Account> fetchSingleAccountwithUsername(String username) {
        return accountRepository.findAccountByusername(username);
    }

    public Account createAnAccount(Account account){

        Account newAccount= accountRepository.insert(new Account(account.getName(),account.getSurname(),account.getUsername(),this.passwordEncoder.encode(account.getPassword()),account.getZooids()));
        return newAccount;
    }
    public Account updateAnAccount(Account account, ObjectId id) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("name", account.getName())
                .set("surname", account.getSurname())
                .set("username", account.getUsername())
                .set("password", this.passwordEncoder.encode(account.getPassword()))
                .set("zooids", account.getZooids());

        mongoTemplate.updateFirst(query, update, Account.class);

        return account;
    }

}
