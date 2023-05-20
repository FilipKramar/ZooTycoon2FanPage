package zootycoon.project.zoowebsite.zoo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import zootycoon.project.zoowebsite.account.Account;

@Service
public class ZooService {
    @Autowired
    private ZooRepository zooRepository;
 @Autowired
 private MongoTemplate mongoTemplate;
    public Zoo createAZoo(Zoo zoo, ObjectId accountId) {
        Zoo newZoo = zooRepository.insert(new Zoo(zoo.getId(), zoo.getZooname(), zoo.getBiome(), zoo.getPictures(), zoo.getAnimalids()));

        Query query = new Query(Criteria.where("id").is(accountId));
        Update update = new Update().push("zooids", newZoo.getId());
        mongoTemplate.updateFirst(query, update, Account.class);

        return newZoo;
    }

    public Zoo updateAZoo(Zoo zoo, ObjectId accountId,ObjectId zooid) {
        Query query = new Query(Criteria.where("id").is(accountId));
        Update update = new Update()
                .push("zooids",zooid)
                .set("zooname", zoo.getZooname())
                .set("biome", zoo.getBiome())
                .set("pictures", zoo.getPictures())
                .set("animalids", zoo.getAnimalids());

        mongoTemplate.updateFirst(query, update, Account.class);

        Query Zooquery = new Query(Criteria.where("id").is(zooid));
        Update Zooupdate = new Update()
                .set("zooname", zoo.getZooname())
                .set("biome", zoo.getBiome())
                .set("pictures", zoo.getPictures())
                .set("animalids", zoo.getAnimalids());

        mongoTemplate.updateFirst(Zooquery, Zooupdate, Zoo.class);

        return zoo;
    }
}
