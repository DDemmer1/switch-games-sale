package de.demmer.dennis.switchgames.model.database;

import de.demmer.dennis.switchgames.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
