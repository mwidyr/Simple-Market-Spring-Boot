package com.example.simplemarket.resource;

import com.example.simplemarket.model.ObjectDetail;
import com.example.simplemarket.repository.ObjectDetailRepository;
import com.example.simplemarket.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/object-detail")
public class ObjectDetailResource {
    Logger logger = LoggerFactory.getLogger(ObjectDetailResource.class);
    @Autowired
    ObjectDetailRepository objectDetailRepository;

    @GetMapping(path = "/getAll")
    public CommonResponse getAllObjectDetail(){
        logger.info("object detail get all");
        List<ObjectDetail> objectDetail = objectDetailRepository.findAll();
        CommonResponse response = new CommonResponse();
        response.setStatus(true);
        if(objectDetail.isEmpty()){
            response.setStatus(false);
            response.setMessage("List Object is null");
            return response;
        }
        response.setObject(objectDetail);
        return response;
    }

    @GetMapping(path = "/get/{id}")
    public CommonResponse getObjectDetail(@PathVariable Integer id){
        logger.info("object detail get by id");
        Optional<ObjectDetail> objectDetail = objectDetailRepository.findById(id);
        CommonResponse response = new CommonResponse();
        response.setStatus(true);
        if(!objectDetail.isPresent()){
            response.setStatus(false);
            response.setMessage("Object Cant be find!");
            return response;
        }
        ObjectDetail object = objectDetail.get();
        response.setObject(object);
        return response;
    }

    @PostMapping(path = "/save")
    public CommonResponse saveObject(@RequestBody ObjectDetail request){
        logger.info("object detail save");
        CommonResponse response = new CommonResponse();
        if(request == null){
            response.setStatus(false);
            response.setMessage("Object Cant be save!");
            return response;
        }
        ObjectDetail objectDetail = objectDetailRepository.save(request);
        response.setStatus(true);
        response.setObject(objectDetail);
        return response;
    }

    @DeleteMapping(path = "/delete")
    public CommonResponse deleteObject(@RequestBody ObjectDetail request){
        logger.info("object detail delete");
        CommonResponse response = new CommonResponse();
        if(request == null){
            response.setStatus(false);
            response.setMessage("Object Cant be delete!");
            return response;
        }
        ObjectDetail objectDetail = objectDetailRepository.save(request);
        response.setStatus(true);
        response.setObject(objectDetail);
        return response;
    }
}
