package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dto.CreateAccessRequest;
import me.shinsunyoung.springbootdeveloper.dto.CreatedAccessResponse;
import me.shinsunyoung.springbootdeveloper.service.RefreshTokenService;
import me.shinsunyoung.springbootdeveloper.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreatedAccessResponse> createNewAccessToken (@RequestBody CreateAccessRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreatedAccessResponse(newAccessToken));
    }

    @DeleteMapping("/api/refresh-token")
    public ResponseEntity deleteRefreshToken() {
        refreshTokenService.delete();

        return ResponseEntity.ok()
                .build();
    }
}
