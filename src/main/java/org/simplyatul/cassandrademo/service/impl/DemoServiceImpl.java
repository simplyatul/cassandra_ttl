package org.simplyatul.cassandrademo.service.impl;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.simplyatul.cassandrademo.config.CassandraProperties;
import org.simplyatul.cassandrademo.model.DemoTable;
import org.simplyatul.cassandrademo.repository.DemoTableRepo;
import org.simplyatul.cassandrademo.service.DemoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DemoServiceImpl implements DemoService {

    private static Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
    private static final Integer DEF_TTL = 60;          // 60 min
    private static final Integer MIN_TTL = 1;           // 1 min
    private static final Integer MAX_TTL = (24*60);     // 1 Day

    @Autowired
    DemoTableRepo demoTableRepo;

    @Autowired
    private CassandraProperties cassandraProp;


    @Override
    public ResponseEntity<?> storeData(JsonNode inRequest) {
        logger.debug("Received request : " + inRequest);

        Integer ttl = DEF_TTL;
        if (null != inRequest.get("ttl")) {
            logger.trace("TTL received : " + inRequest.get("ttl").toString() + " min");
            ttl = inRequest.get("ttl").asInt();

            if (!(MIN_TTL <= ttl && ttl <= MAX_TTL)) {
                ttl = DEF_TTL;
                logger.warn("Incorrect TTL. Using def TTL as 60 min");
            }
        } else {
            logger.warn("Received no TTL. Using def TTL as 60 min");
        }
        // Generate UUID/GUID
        String guid = RandomStringUtils.randomAlphanumeric(cassandraProp.getGuidlen());

        // Save Data in DB
        DemoTable demoData = new DemoTable(guid, inRequest.toString());
        demoTableRepo.save(demoData, (ttl*60), cassandraProp.getDemodatawcl()); // TTL in sec

        logger.debug("GUID returned: " + guid);
        // Return UUID/GUID
        return new ResponseEntity<>(new ApiResponse(guid, null), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?>
        retrieveData(String guid)
                throws IOException {

        if (guid.trim().isEmpty()) {
            logger.error("Empty guid received.");
            return new ResponseEntity<>("guid should not be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<DemoTable> dbRow = demoTableRepo.findById(guid);
        logger.trace("Collected from DB: " + dbRow);

        if (dbRow.isPresent()) {
        	DemoTable demoData = dbRow.get();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(demoData.getContent());
            return new ResponseEntity<>(actualObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}