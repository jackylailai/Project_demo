package org.example.repository;

import org.example.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TipRepository extends JpaRepository<Tip, Long> {

//    @Query("select t from Tip t where t.id <= ?1")
//    Page<Tip> findMore(Long maxId, Pageable pageable);
    @Modifying
    @Transactional
    @Query("update Tip t set t.title = ?1 where t.id = ?2")
    int updateById(String title, Long id);

    Tip findByTipId(Long tipId);

    @Query("SELECT t.title FROM Tip t WHERE t.unitId = :unitId")
    List<String> findTitlesByUnitId(@Param("unitId") Long unitId);

}
