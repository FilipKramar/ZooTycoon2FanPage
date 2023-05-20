package zootycoon.project.zoowebsite.zoo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zootycoon.project.zoowebsite.animal.Animal;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/zoos")
public class ZooController {
    @Autowired
    private ZooService zooService;
    @PostMapping
    public ResponseEntity<Zoo> createAZoo(@RequestBody Map<String, Object> payload) {
        Map<String, Object> zooPayload = (Map<String, Object>) payload.get("zoo");
        String accountIdStr = (String) payload.get("accountId");
        ObjectId accountId = new ObjectId(accountIdStr);

        Zoo zoo = new Zoo();
        zoo.setZooname((String) zooPayload.get("zooname"));
        zoo.setBiome((String) zooPayload.get("biome"));
        zoo.setPictures((List<String>) zooPayload.get("pictures"));
        zoo.setAnimalids((List<Animal>) zooPayload.get("animalIds"));

        return new ResponseEntity<>(zooService.createAZoo(zoo, accountId), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Zoo> updateAZoo(@RequestBody Map<String, Object> payload) {

        Map<String, Object> zooPayload = (Map<String, Object>) payload.get("zoo");
        String accountIdStr = (String) payload.get("accountId");
        ObjectId accountId = new ObjectId(accountIdStr);
        String zooidString = (String) payload.get("zooid");
        ObjectId zooid = new ObjectId(zooidString);

        Zoo zoo = new Zoo();
        zoo.setZooname((String) zooPayload.get("zooname"));
        zoo.setBiome((String) zooPayload.get("biome"));
        zoo.setPictures((List<String>) zooPayload.get("pictures"));
        zoo.setAnimalids((List<Animal>) zooPayload.get("animalIds"));

        return new ResponseEntity<>(zooService.updateAZoo(zoo, accountId,zooid), HttpStatus.CREATED);
    }
}

