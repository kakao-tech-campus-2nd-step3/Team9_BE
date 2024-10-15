package com.helpmeCookies.product.service;

import com.helpmeCookies.global.utils.AwsS3FileUtils;
import com.helpmeCookies.product.dto.FileUploadResponse;
import com.helpmeCookies.product.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final AwsS3FileUtils awsS3FileUtils;
    private final ProductImageRepository productImageRepository;

    @Transactional
    public List<FileUploadResponse> uploadMultiFiles(Long productId, List<MultipartFile> files) throws IOException {
        List<FileUploadResponse> uploadResponses = awsS3FileUtils.uploadMultiImages(files);
        uploadResponses.forEach(response ->
            productImageRepository.save(response.toEntity(productId)));
        return uploadResponses;
    }

    @Transactional
    public void editImages(Long productId, List<MultipartFile> files) throws IOException {
        //우선은 전부 삭제하고 다시 업로드
        //추후에 개선 예정
        productImageRepository.deleteAllByProductId(productId);
        uploadMultiFiles(productId, files);
    }
}
