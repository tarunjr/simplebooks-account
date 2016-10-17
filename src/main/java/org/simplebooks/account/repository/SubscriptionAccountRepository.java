package org.simplebooks.account.repository;

import org.simplebooks.account.domain.SubscriptionAccount;

import org.springframework.data.repository.CrudRepository;

public interface SubscriptionAccountRepository extends CrudRepository<SubscriptionAccount, Long> {
}
