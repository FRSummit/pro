package com.frsummit.hr2.jpa_repository;

import com.frsummit.hr2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository("userRepository")
//@Transactional
//@Service
//public interface  UserRepository extends CrudRepository<User, Long> {

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);
    User findById(String id);

    @Modifying
    @Query("UPDATE User c SET c.name = :name WHERE c.email = :email")
    //public int updateName(@Param("email") String email, @Param("name") String name);
    public void updateName(@Param("email") String email, @Param("name") String name);

//    @Modifying
//    @Query("SELECT u FROM User AS u WHERE u.user_id = :userId")
//    //public int updateName(@Param("email") String email, @Param("name") String name);
//    public void getSingleId(@Param("userId") String email);



}