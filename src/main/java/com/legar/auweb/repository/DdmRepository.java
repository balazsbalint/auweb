
package com.legar.auweb.repository;

import com.legar.auweb.entity.Ddm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DdmRepository extends JpaRepository<Ddm, Long> {

    /**
     * Finds DDMs by Database ID.
     */
    List<Ddm> findByDatabaseId(int databaseId);

    /**
     * Finds DDMs by File ID within a specific database.
     */
    List<Ddm> findByDatabaseIdAndFileId(int databaseId, int fileId);
}
