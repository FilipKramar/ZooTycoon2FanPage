package zootycoon.project.zoowebsite.animal;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import zootycoon.project.zoowebsite.zoo.Zoo;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Animal createAnAnimal(ObjectId zooid,Animal animal){
        Animal newAnimal= animalRepository.insert(new Animal(animal.getAnimalname(),animal.getAnimalbiome(),animal.getDiet(),animal.getPicture()));

        mongoTemplate.update(Zoo.class)
                .matching(Criteria.where("id").is(zooid))
                .apply(new Update().push("animalids").value(newAnimal))
                .first();

        return animal;
    }
}
