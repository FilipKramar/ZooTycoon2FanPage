package zootycoon.project.zoowebsite.animal;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @PostMapping
    public ResponseEntity<Animal> createAZoo(@RequestBody Map<String, Object> payload) {
        Map<String, Object> animalpayload = (Map<String, Object>) payload.get("animal");
        String zooidString = (String) payload.get("zooid");
        ObjectId zooid = new ObjectId(zooidString);

        Animal animal= new Animal();
        animal.setAnimalname((String) animalpayload.get("animalname"));
        animal.setAnimalbiome((String) animalpayload.get("animalbiome"));
        animal.setDiet((String) animalpayload.get("diet"));
        animal.setPicture((String) animalpayload.get("picture"));

        return new ResponseEntity<>(animalService.createAnAnimal(zooid,animal), HttpStatus.CREATED);
    }

}
