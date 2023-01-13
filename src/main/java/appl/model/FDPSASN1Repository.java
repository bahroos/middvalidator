package appl.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FDPSASN1Repository extends JpaRepository<AllFDPSASN1Files, Long> {

}
