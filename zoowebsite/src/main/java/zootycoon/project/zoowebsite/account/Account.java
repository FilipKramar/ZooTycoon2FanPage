package zootycoon.project.zoowebsite.account;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import zootycoon.project.zoowebsite.zoo.Zoo;

import java.util.List;

@Document(collection = "Accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private ObjectId id;
    private String username;
    private String name;
    private String surname;
    private String password;
    @DocumentReference
    private List<Zoo> zooids;


    public Account(String username, String name, String surname, String password, List<Zoo> zooids) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.zooids = zooids;
    }

}
