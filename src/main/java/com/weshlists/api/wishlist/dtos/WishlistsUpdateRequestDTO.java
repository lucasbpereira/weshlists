package com.weshlists.api.wishlist.dtos;

import java.time.LocalDateTime;
import java.util.Date;

import com.weshlists.api.user.models.Users;

public record WishlistsUpdateRequestDTO(
    int id,
    String donor_email,
    LocalDateTime updatedAt,
    String titleProduct,
    String subtitle,
    String linkShopee,
    String linkAmazon,
    String linkOutros,
    String thumbnail
) {

}
