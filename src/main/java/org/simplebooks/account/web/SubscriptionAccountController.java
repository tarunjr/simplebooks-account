package org.simplebooks.account.web;

import org.simplebooks.account.repository.SubscriptionAccountRepository;
import org.simplebooks.account.domain.SubscriptionAccount;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.ArrayList;

@RestController
public class SubscriptionAccountController {

    private final SubscriptionAccountRepository respository;
    @Autowired
    public SubscriptionAccountController(SubscriptionAccountRepository respository) {
      this.respository = respository;
    }

    @RequestMapping(path="/subscriptionaccounts", method=RequestMethod.GET)
    public List<SubscriptionAccount> getAll(){
       List<SubscriptionAccount> accounts = new ArrayList<SubscriptionAccount>();
       for(SubscriptionAccount account : respository.findAll()) {
          accounts.add(account);
       }
       return accounts;
    }

    @RequestMapping(path="/subscriptionaccounts/{id}", method=RequestMethod.GET)
    public ResponseEntity<SubscriptionAccount> get(@PathVariable(name="id", required=true) String id){
        long dbId = Long.parseLong(id);
        if (respository.exists(dbId)) {
          SubscriptionAccount sa = respository.findOne(dbId);
          return new ResponseEntity<SubscriptionAccount>(sa, HttpStatus.OK);
        } else {
          return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path="/subscriptionaccounts", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<SubscriptionAccount> create(@RequestBody SubscriptionAccount sa){
        respository.save(sa);
        return new ResponseEntity<SubscriptionAccount>(sa,HttpStatus.CREATED);
    }
    @RequestMapping(path="/subscriptionaccounts/{id}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity update(@PathVariable(name="id", required=true) String id,
                                 @RequestBody SubscriptionAccount saUpdated){
        long dbId = Long.parseLong(id);
        if (respository.exists(dbId)) {
          SubscriptionAccount sa = respository.findOne(dbId);
          sa.setQuantity(saUpdated.getQuantity());
          sa.setEditionCode(saUpdated.getEditionCode());
          sa.setUnit(saUpdated.getUnit());
          sa.setStatus(saUpdated.getStatus());
          sa.setStatus(saUpdated.getStatus());
          sa.setQuantity(saUpdated.getQuantity());
          respository.save(sa);
          return new ResponseEntity<SubscriptionAccount>(HttpStatus.OK);
      } else {
          return new ResponseEntity(HttpStatus.NOT_FOUND);
      }
    }
    @RequestMapping(path="/subscriptionaccounts/{id}", method=RequestMethod.DELETE)
    public ResponseEntity update(@PathVariable(name="id", required=true) String id){
        long dbId = Long.parseLong(id);
        if (respository.exists(dbId)){
          respository.delete(dbId);
          return new ResponseEntity(HttpStatus.OK);
        } else {
          return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }



  }
