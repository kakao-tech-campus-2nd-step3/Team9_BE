package com.helpmeCookies.product.service;

import com.helpmeCookies.global.utils.AwsS3FileUtils;
import com.helpmeCookies.product.dto.FileUploadResponse;
import com.helpmeCookies.product.entity.Category;
import com.helpmeCookies.product.entity.Product;
import com.helpmeCookies.product.repository.ProductImageRepository;
import com.helpmeCookies.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ProductImageServiceTest {
    @MockBean
    private AwsS3FileUtils awsS3FileUtils;

    private ProductImageService productImageService;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ProductImageRepository productImageRepository;

    @BeforeEach
    void setUp() {
        productImageService = new ProductImageService(awsS3FileUtils, productImageRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("상품 이미지 S3 서버에 업로드")
    void uploadMultiFiles() throws IOException {
        given(productRepository.findById(any()))
                .willReturn(Optional.of(new Product("더미",Category.CERAMIC,"100",10000L,"테스트항목","테스트 주소",
                        null,null)));
        MockMultipartFile file1 = new MockMultipartFile("test1","img1.jpg","image/jpeg","image content".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("test2","img2.jpg","image/jpeg","image content".getBytes());
        List<MultipartFile> files = Arrays.asList(file1,file2);

        List<FileUploadResponse> expected = new ArrayList<>();
        expected.add(new FileUploadResponse("url1","1111"));
        expected.add(new FileUploadResponse("url2","2222"));
        when(awsS3FileUtils.uploadMultiImages(files)).thenReturn(expected);

        List<FileUploadResponse> actual = productImageService.uploadMultiFiles(1L,files);
        assertEquals(2,actual.size(), "배열의 크기는 2여야함");
    }
}
