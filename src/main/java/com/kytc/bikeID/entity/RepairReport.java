package com.kytc.bikeID.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "report")
public class RepairReport {

    @MongoId
    private ObjectId id;

    private Integer bikeId;

    private Integer userId;

    private String bikeName;

    private String serviceWork;

    private Boolean warrantyWork;

    private String workShop;

    private String clientName;

    private String servicemenName;

    private String replacementParts;

    private Integer workPrise;

    private String technicalStatus;

    private String serviceMenComments;

}
