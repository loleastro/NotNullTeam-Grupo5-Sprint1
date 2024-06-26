package org.mercadolibre.NotNullTeam.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mercadolibre.NotNullTeam.DTO.response.seller.SellerFollowersCountDto;
import org.mercadolibre.NotNullTeam.service.ISellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SellerController {
    final ISellerService iSellerService;

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerFollowersCountDto> getFollowersCount(
            @PathVariable @Valid @Positive(message = "El id  debe ser mayor a cero.") Long userId) {
        return ResponseEntity.ok(iSellerService.getFollowersCount(userId));
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> getListFollowers(
            @PathVariable @Valid @Positive(message = "El id  debe ser mayor a cero.") Long userId,
            @RequestParam(value = "order", required = false, defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(
                iSellerService.getListFollowersOrdered(userId, order),
                HttpStatus.OK
        );
    }
}
