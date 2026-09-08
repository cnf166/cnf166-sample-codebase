package vn.cnf166.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.cnf166.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
