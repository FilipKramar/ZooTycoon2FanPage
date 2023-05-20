package zootycoon.project.zoowebsite.account;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zootycoon.project.zoowebsite.animal.Animal;
import zootycoon.project.zoowebsite.zoo.Zoo;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    @Autowired
    private AccountService accountService;

    //registration check if username is unique
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<List<Account>>(accountService.fetchAllAccounts(), HttpStatus.OK);
    }
    //myAccountDetails da dohvati zoopodatke itd...
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> getSingleAccount(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Account>>(accountService.fetchSingleAccount(id),HttpStatus.OK);

    }
    //forgotten password , username validation message
    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<Account>> getSingleAccountUsername(@PathVariable String username){
        return new ResponseEntity<Optional<Account>>(accountService.fetchSingleAccountwithUsername(username),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Account> createAnAccount(@RequestBody Account payload){
        return new ResponseEntity<>(accountService.createAnAccount(payload), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Account> updateAnAccount(@RequestBody Map<String,Object> payload){
        Map<String, Object> accountPayLoad = (Map<String, Object>) payload.get("account");
        String accountIdStr = (String) payload.get("accountId");
        ObjectId accountId = new ObjectId(accountIdStr);

       Account account=new Account();
       account.setUsername((String) accountPayLoad.get("username"));
        account.setName((String) accountPayLoad.get("name"));
        account.setSurname((String) accountPayLoad.get("surname"));
        account.setPassword((String)accountPayLoad.get("password"));
        account.setZooids((List<Zoo>) accountPayLoad.get("zooids"));

        return new ResponseEntity<>(accountService.updateAnAccount(account, accountId), HttpStatus.CREATED);
    }
 }
