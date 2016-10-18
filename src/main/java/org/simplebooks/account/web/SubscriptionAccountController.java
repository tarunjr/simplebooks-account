package org.simplebooks.account.web;

import org.simplebooks.account.repository.SubscriptionAccountRepository;
import org.simplebooks.account.domain.SubscriptionAccount;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

@RestController
public class SubscriptionAccountController {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionAccountController.class);

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
        log.info("get :account=" + id);
        SubscriptionAccount sa = getAccount(id);
        return new ResponseEntity<SubscriptionAccount>(sa, HttpStatus.OK);
    }

    @RequestMapping(path="/subscriptionaccounts", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<SubscriptionAccount> create(@RequestBody SubscriptionAccount sa){
        log.info("post account");
        respository.save(sa);
        return new ResponseEntity<SubscriptionAccount>(sa,HttpStatus.CREATED);
    }

    @RequestMapping(path="/subscriptionaccounts/{id}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity update(@PathVariable(name="id", required=true) String id,
                                 @RequestBody SubscriptionAccount saUpdated){
        log.info("update :account=" + id);
        SubscriptionAccount sa = getAccount(id);

        sa.setQuantity(saUpdated.getQuantity());
        sa.setEditionCode(saUpdated.getEditionCode());
        sa.setUnit(saUpdated.getUnit());
        sa.setStatus(saUpdated.getStatus());
        sa.setStatus(saUpdated.getStatus());
        sa.setQuantity(saUpdated.getQuantity());
        respository.save(sa);
        return new ResponseEntity<SubscriptionAccount>(HttpStatus.OK);
    }

    @RequestMapping(path="/subscriptionaccounts/{id}", method=RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name="id", required=true) String id){
        log.info("delete :account=" + id);
        SubscriptionAccount sa = getAccount(id);
        respository.delete(sa);
        return new ResponseEntity(HttpStatus.OK);
    }

    private SubscriptionAccount getAccount(String accountId) {
      long dbId = Long.parseLong(accountId);
      if (!respository.exists(dbId)) {
        log.debug("Account not found:account=" + accountId);
        throw new AccountNotFoundException(accountId);
      } else {
        return respository.findOne(dbId);
      }
    }
  }
  @ResponseStatus(HttpStatus.NOT_FOUND)
  class AccountNotFoundException extends RuntimeException {
  	public AccountNotFoundException(String accountId) {
  		super("Account not found exception: id=" + accountId);
  	}
  }
