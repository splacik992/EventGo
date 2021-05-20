package com.pali.eventgo.repository;

import com.pali.eventgo.entity.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends JpaRepository<Long, Localization> {


}
