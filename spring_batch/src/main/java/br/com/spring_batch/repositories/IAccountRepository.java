package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long> {

    /**
     * Method responsible to find an {@link Account} by perfoming an Like at Email field.
     * @param email - the email to performe the like operation.
     * @return return the Account found.
     */
    Account findByEmailIsLike(String email);
}
