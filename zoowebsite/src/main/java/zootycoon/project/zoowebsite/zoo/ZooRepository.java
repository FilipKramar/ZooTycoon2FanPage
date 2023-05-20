package zootycoon.project.zoowebsite.zoo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooRepository extends MongoRepository<Zoo, ObjectId> {
}
