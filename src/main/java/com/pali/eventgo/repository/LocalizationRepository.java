package com.pali.eventgo.repository;

import com.pali.eventgo.entity.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {

    @Query(value = "SELECT * FROM localization where name =:place",nativeQuery = true)
    Localization findByName(@Param("place") String placeName);
}
