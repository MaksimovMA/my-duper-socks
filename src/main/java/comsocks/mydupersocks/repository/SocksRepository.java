package comsocks.mydupersocks.repository;



import comsocks.mydupersocks.entity.ColorSocks;
import comsocks.mydupersocks.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


public interface SocksRepository extends JpaRepository<Socks, Long> {

    Socks findSocksByColorAndCottonPart (ColorSocks color, Integer cotton);
    List<Socks> findSocksByColor(ColorSocks color);
}
