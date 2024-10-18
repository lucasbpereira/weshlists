package com.weshlists.api.wishlist.dtos;

import java.time.LocalDateTime;
import java.util.Date;

import com.weshlists.api.user.models.Users;

public record WishlistsRequestDTO(
    String titleProduct,
    String subtitle,
    String linkShopee,
    String linkAmazon,
    String linkOutros,
    String thumbnail,
    String donor_email,
    int user_id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
