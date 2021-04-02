package com.safetrust.interview.contact.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.safetrust.interview.contact.models.Contact;

/**
 * 
 * repository to manage contact
 *
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Modifying
    @Query("DELETE FROM Contact WHERE id in :ids")
    void deleteAllContactWithIds(@Param("ids") Collection<Long> ids);

}
