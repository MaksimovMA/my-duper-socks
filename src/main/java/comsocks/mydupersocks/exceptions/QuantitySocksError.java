package comsocks.mydupersocks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="На складе нет запрашиваемого количества носков")
public class QuantitySocksError extends RuntimeException {
    public QuantitySocksError() {
        super(" ошибку 400 выдавалось сообщение??");
    }
}
