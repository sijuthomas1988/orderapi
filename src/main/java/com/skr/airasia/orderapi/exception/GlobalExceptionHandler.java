package com.skr.airasia.orderapi.exception;

import com.skr.airasia.orderapi.model.TransactionStatus;
import com.skr.airasia.orderapi.model.ErrorDetails;
import com.skr.airasia.orderapi.model.OrderErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Class to handle exceptions gracefully and construct error model
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handler used when {@link ServiceException} is  thrown
     *
     * @param e          service exception
     * @param webRequest web request interceptor to fetch metadata of request
     * @return {@link ResponseEntity} of type {@link ErrorDetails}
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceExceptionHandler(ServiceException e, WebRequest webRequest) {
        if (e.getCode().equalsIgnoreCase(OrderErrorCode.REPOSITORY_ERROR.getCode())) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), OrderErrorCode.REPOSITORY_ERROR.getCode(),
                    OrderErrorCode.REPOSITORY_ERROR.getMessage(), TransactionStatus.valueOf(e.getStatus()));
            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_MODIFIED);
        } else if (e.getCode().equalsIgnoreCase(OrderErrorCode.UNABLE_TO_PROCESS_ORDER.getCode())) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), OrderErrorCode.UNABLE_TO_PROCESS_ORDER.getCode(),
                    OrderErrorCode.UNABLE_TO_PROCESS_ORDER.getMessage(), TransactionStatus.valueOf(e.getStatus()));
            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_MODIFIED);
        } else {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), OrderErrorCode.UNEXPECTED_ERROR.getCode(),
                    OrderErrorCode.UNEXPECTED_ERROR.getMessage(), TransactionStatus.REJECTED);
            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handler used when {@link Exception} is  thrown
     *
     * @param e          exception
     * @param webRequest web request interceptor to fetch metadata of request
     * @return {@link ResponseEntity} of type {@link ErrorDetails}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), OrderErrorCode.INTERNAL_SERVICE_ERROR.getMessage(), OrderErrorCode.INTERNAL_SERVICE_ERROR.getMessage(),
                TransactionStatus.REJECTED);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

