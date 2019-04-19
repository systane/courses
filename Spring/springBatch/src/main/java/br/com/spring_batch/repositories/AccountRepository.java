package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Method responsible to find an {@link Account} by perfoming an Like at Email field.
     * @param email - the email to performe the like operation.
     * @return return the Account found.
     */
    Account findByEmailIsLike(String email);

    /**
     * This method search for {@link Account}s records that contains
     * in the email field the {@link String} email.
     * @param email
     * @return {@link List}<{@link Account}> found.
     */
    List<Account> findByEmailLike(String email);
}
