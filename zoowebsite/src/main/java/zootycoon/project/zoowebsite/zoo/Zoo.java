package zootycoon.project.zoowebsite.zoo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import zootycoon.project.zoowebsite.animal.Animal;

import java.util.List;

@Document(collection = "Zoos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zoo {
    @Id
    private ObjectId id;
    private String zooname;
    private String biome;
    private List<String> pictures;

    @DocumentReference
    private List<Animal> animalids;


    public Zoo(String zooname, String biome, List<String> pictures, List<Animal> animalId) {
        this.zooname = zooname;
        this.biome = biome;
        this.pictures = pictures;
        this.animalids = animalId;
    } }



