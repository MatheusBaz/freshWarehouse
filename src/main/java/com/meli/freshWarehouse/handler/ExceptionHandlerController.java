package com.meli.freshWarehouse.handler;

import com.meli.freshWarehouse.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController {

    private String BAD_REQUEST = "Bad Request.";

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(NotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Data object not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler({WarehouseNotFoundException.class})
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(WarehouseNotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Warehouse not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }
    @ExceptionHandler({InboundOrderNotFoundException.class})
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(InboundOrderNotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Inbound Order not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(FileNotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Data object not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title(BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Bad Request. Param " + e.getName() +
                        " has invalid value: " + e.getValue())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title(BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .message("JSON parse error: Cannot deserialize value of type")
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(HttpServerErrorException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title(BAD_REQUEST)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal server Error")
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ExceededStock.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(ExceededStock e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title(BAD_REQUEST)
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(ItsNotBelongException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(ItsNotBelongException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Unprocessable Entity.")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionDetails> handlerMissingParamEx(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Unprocessable Entity.")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler({EmptySectionListException.class})
    public ResponseEntity<ExceptionDetails> handlerEmptySectionListEx(EmptySectionListException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("This Section is Empty.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }
    @ExceptionHandler({SectionIdNotFoundException.class})
    public ResponseEntity<ExceptionDetails> handlerSectionIdNotFoundEx(SectionIdNotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Section ID not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }
    @ExceptionHandler({InvalidSectionNameException.class})
    public ResponseEntity<ExceptionDetails> handlerInvalidSectionNameEx(InvalidSectionNameException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("This Section name isn't valid.")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );

    }

    @ExceptionHandler({InvalidBatchSaleOffParam.class})
    public ResponseEntity<ExceptionDetails> handlerInvalidBatchSaleOff(InvalidBatchSaleOffParam ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Error in Batch sale off!")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );

    }

}
