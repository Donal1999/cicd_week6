package ie.atu.week5_githubactions.controller.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExecptionDetails>> showErrors(MethodArgumentNotValidException ex)
    {
        List<ExecptionDetails> errors = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors())
        {
            //return a json object
            ExecptionDetails details = new ExecptionDetails(error.getField(), error.getDefaultMessage());
            errors.add(details);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(DuplicateError.class)
    public ResponseEntity<ExecptionDetails> showDupError(DuplicateError ex){
        // return a json object
        ExecptionDetails execptionDetails = new ExecptionDetails();
        execptionDetails.setFieldName("Passenger ID ");
        execptionDetails.setFieldValue(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(execptionDetails);
    }
}
