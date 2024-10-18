package com.weshlists.api.wishlist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weshlists.api.user.models.Users;
import com.weshlists.api.wishlist.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{

        Optional<Wishlist> findById(int userId);

}
