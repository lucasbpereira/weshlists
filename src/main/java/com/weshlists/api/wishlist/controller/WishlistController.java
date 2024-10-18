package com.weshlists.api.wishlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.weshlists.api.exceptions.BadCredentialsLoginException;
import com.weshlists.api.exceptions.EmptyImageException;
import com.weshlists.api.user.dtos.AutheticationDto;
import com.weshlists.api.wishlist.dtos.UploadThumbnailResponseDto;
import com.weshlists.api.wishlist.dtos.WishlistsRequestDTO;
import com.weshlists.api.wishlist.dtos.WishlistsUpdateRequestDTO;
import com.weshlists.api.wishlist.models.Wishlist;
import com.weshlists.api.wishlist.services.WishlistService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    WishlistService service;
    
    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody WishlistsRequestDTO wishlistDTO) {
        // Processar o wishlistDTO, como salvar no banco de dados
        
        return ResponseEntity.ok().body(service.createWishlist(wishlistDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Wishlist>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping("/update-item")
    public ResponseEntity<Wishlist> updateItem(@RequestBody WishlistsUpdateRequestDTO wishlistDTO) {
        // Processar o wishlistDTO, como salvar no banco de dados
        return ResponseEntity.ok().body(service.updateItem(wishlistDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        // Processar o wishlistDTO, como salvar no banco de dados
        service.delete(id);
        return ResponseEntity.noContent().build();
   }

    @PutMapping("/update")
    public ResponseEntity<Wishlist> updateWishlist(@RequestBody WishlistsUpdateRequestDTO wishlistDTO) {
        // Processar o wishlistDTO, como salvar no banco de dados
        return ResponseEntity.ok().body(service.updateWishlist(wishlistDTO));
    }

    @Value("${image.upload.directory}")
    private String uploadDirectory;

    @PostMapping("/upload")
    public ResponseEntity<UploadThumbnailResponseDto> uploadImage(@RequestParam("file") MultipartFile file) {
        try { 
            // Verificar se o arquivo não está vazio
            if (file.isEmpty()) {
                throw new EmptyImageException("Imagem não encontrada");
            }

            // Gerar um nome de arquivo único para evitar conflitos
            String fileName = UUID.randomUUID().toString() + "." + getFileExtension(file);
            Path filePath = Paths.get(uploadDirectory + File.separator + fileName);

            // Criar o diretório se não existir
            Files.createDirectories(filePath.getParent());

            // Salvar o arquivo no diretório
            Files.write(filePath, file.getBytes());

            // Retornar o link de visualização da imagem
            UploadThumbnailResponseDto response = new UploadThumbnailResponseDto();
            String fileUrl = "wishlist/view/" + fileName;
            response.setLink(fileUrl);
            return ResponseEntity.ok().body(response);

        } catch (IOException e) {
            throw new EmptyImageException("Imagem não encontrada");
        }
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<byte[]> viewImage(@PathVariable String fileName) {
        try {
            // Caminho do arquivo
            Path filePath = Paths.get(uploadDirectory + File.separator + fileName);

            // Ler os bytes da imagem
            byte[] imageBytes = Files.readAllBytes(filePath);

            // Retornar a imagem em formato de bytes
            return ResponseEntity
                    .ok()
                    .header("Content-Type", Files.probeContentType(filePath))
                    .body(imageBytes);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }


    private String getFileExtension(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        return originalName != null ? originalName.substring(originalName.lastIndexOf(".") + 1) : "";
    }
}
