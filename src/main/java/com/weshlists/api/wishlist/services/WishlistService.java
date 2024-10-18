package com.weshlists.api.wishlist.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weshlists.api.user.dtos.AutheticationDto;
import com.weshlists.api.user.models.Users;
import com.weshlists.api.user.repositories.UserRepository;
import com.weshlists.api.wishlist.WishlistRepository;
import com.weshlists.api.wishlist.dtos.WishlistsRequestDTO;
import com.weshlists.api.wishlist.dtos.WishlistsUpdateRequestDTO;
import com.weshlists.api.wishlist.models.Wishlist;

import jakarta.validation.Valid;

@Service
public class WishlistService {
    
    @Autowired
    WishlistRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Wishlist createWishlist(WishlistsRequestDTO wishlistDTO) {
        System.out.println(wishlistDTO.user_id());
        Users user = userRepository.findById(wishlistDTO.user_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = new Wishlist();
        wishlist.setTitleProduct(wishlistDTO.titleProduct());
        wishlist.setSubtitle(wishlistDTO.subtitle());
        wishlist.setLinkShopee(wishlistDTO.linkShopee());
        wishlist.setLinkAmazon(wishlistDTO.linkAmazon());
        wishlist.setLinkOutros(wishlistDTO.linkOutros());
        wishlist.setDonor_email(wishlistDTO.donor_email());
        wishlist.setThumbnail(wishlistDTO.thumbnail());
        wishlist.setCreatedAt(wishlistDTO.createdAt() != null ? wishlistDTO.createdAt() : LocalDateTime.now());
        wishlist.setUsers(user);

        return repository.save(wishlist);
    }

    public List<Wishlist> getAll() {
        
        return repository.findAll();
    }

    public Wishlist updateWishlist(WishlistsUpdateRequestDTO wishlistDTO) {
        
        Wishlist existingWishlist = repository.findById(wishlistDTO.id())
        .orElseThrow(() -> new RuntimeException("Wishlist not found with id " + wishlistDTO.id()));

        existingWishlist.setUpdatedAt(LocalDateTime.now());
        existingWishlist.setDonor_email(wishlistDTO.donor_email());

        return repository.save(existingWishlist);
    }

    public Wishlist updateItem(WishlistsUpdateRequestDTO wishlistDTO) {
        Wishlist existingWishlist = repository.findById(wishlistDTO.id())
        .orElseThrow(() -> new RuntimeException("Wishlist not found with id " + wishlistDTO.id()));

        existingWishlist.setId(wishlistDTO.id());
        existingWishlist.setUpdatedAt(LocalDateTime.now());
        existingWishlist.setDonor_email(wishlistDTO.donor_email());
        existingWishlist.setTitleProduct(wishlistDTO.titleProduct());
        existingWishlist.setSubtitle(wishlistDTO.subtitle());
        existingWishlist.setLinkShopee(wishlistDTO.linkShopee());
        existingWishlist.setLinkAmazon(wishlistDTO.linkAmazon());
        existingWishlist.setLinkOutros(wishlistDTO.linkOutros());
        existingWishlist.setThumbnail(wishlistDTO.thumbnail());

        return repository.save(existingWishlist);
    }

    public void delete(int id) {
        repository.deleteById(id);
        return;
    }

}
