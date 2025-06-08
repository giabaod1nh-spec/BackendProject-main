package com.example.trainning.point.repository;

import com.example.trainning.point.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , String> {
    Optional<User> findByEmail(String email);
    //Optional<User> findByClassId(Long class_id);

    @Query("""
               select count(us) from User us join us.roles r
                where us.classManager.id = :id and r.name in ('STUDENT' , 'MONITOR')
            """)
    Long countByStudent(@Param("id") Long id );

    @Query("""
                select us from User us where
                (:keySearch is null or us.email like concat('%', :keySearch, '%') or us.fullName like concat('%', :keySearch, '%'))
                and (:roleName is null or exists (
                    select 1 from 
                       us.roles role 
                          where role.name = :roleName                           
                )) 
                and (:isDelete is null or us.isDelete = :isDelete )
                order by us.createDate asc         
            """)
    List<User> findAll(@Param("keySearch") String keySearch,
                       @Param("roleName") String roleName,
                       @Param("isDelete") Boolean isDelete);


    @Query("""
                select us from User us join us.roles r where us.classManager.id = :id  order by us.createDate asc
           """)
    List<User> findByClass(@Param("id") Long id);


    @Query("""
                select us from User us join us.roles where
                (:keySearch is null or us.email like concat('%', :keySearch, '%') or us.fullName like concat('%', :keySearch, '%'))
                and (:roleName is null or exists (
                    select 1 from 
                       us.roles role 
                          where role.name = :roleName                           
                )) 
                and (:isDelete is null or us.isDelete = :isDelete )
                and (:codeClass is null or us.classManager.codeClass = :codeClass)
                order by us.createDate asc           
            """)
    Page<User> findAllPage(@Param("keySearch") String keySearch,
                           @Param("roleName") String roleName,
                           @Param("isDelete") Boolean isDelete,
                           @Param("codeClass") String codeClass,
                           Pageable pageable);


    @Query("""
                select us from User us join us.roles where
                (:keySearch is null or us.email like concat('%', :keySearch, '%') or us.fullName like concat('%', :keySearch, '%'))
                and (:roleName is null or exists (
                    select 1 from 
                       us.roles role 
                          where role.name = :roleName                           
                )) 
                and (:isDelete is null or us.isDelete = :isDelete )
                and (:codeClass is null or us.classManager.codeClass = :codeClass)
                order by us.createDate asc           
            """)

    List<User>findAlls(@Param("keySearch") String keySearch,
                       @Param("roleName") String roleNames,
                       @Param("isDelete") Boolean isDelete,
                       @Param("codeClass") String codeClass
                       );

    @Query("""
                select us from User us join us.roles where
                (:keySearch is null or us.email like concat('%', :keySearch, '%') or us.fullName like concat('%', :keySearch, '%'))
                and (:roleName is null or exists (
                    select 1 from 
                       us.roles role 
                          where role.name = :roleName                           
                )) 
                and (:isDelete is null or us.isDelete = :isDelete )
                and (:codeClass is null or us.classManager.codeClass = :codeClass)
                order by us.createDate asc           
            """)
    Page<User>findAllsPage(@Param("keySearch") String keySearch,
                           @Param("roleName") String roleNames,
                           @Param("isDelete") Boolean isDelete,
                           @Param("codeClass") String codeClass,
                           Pageable pageable);
}

