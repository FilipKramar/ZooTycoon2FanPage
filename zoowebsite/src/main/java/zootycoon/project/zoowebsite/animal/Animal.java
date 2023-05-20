package zootycoon.project.zoowebsite.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    private ObjectId id;
    private String animalname;
    private String animalbiome;
    private String diet;
    private String picture;

    public Animal(String animalname, String animalbiome, String diet, String picture) {
        this.animalname = animalname;
        this.animalbiome = animalbiome;
        this.diet = diet;
        this.picture = picture;
    }
}
