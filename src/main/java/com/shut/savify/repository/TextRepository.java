package com.shut.savify.repository;

import com.shut.savify.entity.Text;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends MongoRepository<Text, String> {

}
