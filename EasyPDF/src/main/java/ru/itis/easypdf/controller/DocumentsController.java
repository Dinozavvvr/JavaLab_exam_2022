package ru.itis.easypdf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.easypdf.dto.BudgetPdfDto;
import ru.itis.easypdf.dto.GrantPdfDto;
import ru.itis.easypdf.dto.OtchisleniePdfDto;
import ru.itis.easypdf.security.details.UserDetailsImpl;
import ru.itis.easypdf.service.DocumentService;

/**
 * created: 22-01-2022 - 17:44
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DocumentsController {

    private final DocumentService documentService;
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/generate/grant")
    public ResponseEntity<?> generateGrant(@RequestBody GrantPdfDto pdfDto) {
        return generatePdfAndConvertToResponse(pdfDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/generate/budget")
    public ResponseEntity<?> generateBudget(@RequestBody BudgetPdfDto pdfDto) {
        return generatePdfAndConvertToResponse(pdfDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/generate/otchislenie")
    public ResponseEntity<?> generateOtchislenie(@RequestBody OtchisleniePdfDto pdfDto) {
        return generatePdfAndConvertToResponse(pdfDto);
    }

    private <T> ResponseEntity<?> generatePdfAndConvertToResponse(T pdfDto) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Long userId = ((UserDetailsImpl) authentication.getDetails()).getUser().getId();

        byte[] data = documentService.generateDocument(pdfDto, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
