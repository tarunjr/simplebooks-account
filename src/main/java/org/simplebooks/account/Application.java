package org.simplebooks.account;

import org.simplebooks.account.repository.SubscriptionAccountRepository;
import org.simplebooks.account.domain.SubscriptionAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
      SpringApplication.run(Application.class,args);
  }
  @Bean
	public CommandLineRunner demo(SubscriptionAccountRepository repository) {
		return (args) -> {
        SubscriptionAccount ac1 = new SubscriptionAccount();
        repository.save(ac1);
        log.info(ac1.getAccountIdentifier());
        SubscriptionAccount ac2 = new SubscriptionAccount();
        repository.save(ac2);
        SubscriptionAccount acc1=repository.findOne(1L);
        SubscriptionAccount acc2=repository.findOne(2L);
        log.info(acc1.getAccountIdentifier());
        log.info(acc2.getAccountIdentifier());
    };
  }
}
