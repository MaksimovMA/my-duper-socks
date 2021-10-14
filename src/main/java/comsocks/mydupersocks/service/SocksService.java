package comsocks.mydupersocks.service;


import comsocks.mydupersocks.entity.ColorSocks;
import comsocks.mydupersocks.entity.Socks;
import comsocks.mydupersocks.exceptions.QuantitySocksError;
import comsocks.mydupersocks.repository.SocksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksService {
    private final SocksRepository socksRepository;

    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }


    public boolean addSocks(Socks socks) {
        socksRepository.save(socks);
        return true;
    }


    public void addSocks(ColorSocks color, Integer cottonPart, Integer quantity) {
        if (socksRepository.findSocksByColorAndCottonPart(color, cottonPart) != null) {
            Socks socksFormDB = socksRepository.findSocksByColorAndCottonPart(color, cottonPart);
            socksFormDB.setQuantity(socksFormDB.getQuantity() + quantity);
            socksRepository.save(socksFormDB);
        } else {
            Socks newSocks = new Socks(color, cottonPart, quantity);
            socksRepository.save(newSocks);
        }
    }

    public void deleteSocks(ColorSocks color, Integer cottonPart, Integer quantity) {
        if (socksRepository.findSocksByColorAndCottonPart(color, cottonPart) != null && quantity > 0) {
            Socks socksFormDB = socksRepository.findSocksByColorAndCottonPart(color, cottonPart);
            if (socksFormDB.getQuantity() >= quantity) {
                socksFormDB.setQuantity(socksFormDB.getQuantity() - quantity);
                socksRepository.save(socksFormDB);
            } else {
                throw new QuantitySocksError();
            }
        } else {
            throw new QuantitySocksError();
        }
    }

    public List<Socks> findAll() {
        return socksRepository.findAll();
    }

    public Integer getCountSocksType(ColorSocks color, String operation, Integer cottonPart) {
        List<Socks> listSocks = socksRepository.findSocksByColor(color);
        int count = 0;
        for (int i = 0; i < listSocks.size(); i++) {
            switch (operation.toUpperCase()) {
                case "MORETHAN":
                    if (listSocks.get(i).getCottonPart() > cottonPart) {
                        count = count + listSocks.get(i).getQuantity();
                    }
                    break;
                case "LESSTHAN":
                    if (listSocks.get(i).getCottonPart() < cottonPart) {
                        count = count + listSocks.get(i).getQuantity();
                    }
                    break;
                case "EQUAL":
                    if (listSocks.get(i).getCottonPart() == cottonPart) {
                        count = count + listSocks.get(i).getQuantity();
                    }
                    break;
            }
        }
        return count;
    }
}

