/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import models.Storage;

/**
 *
 * @author RED-HAWK
 */
public class StorageService {

    private static StorageService storageService;
    private static AmazonS3 s3Client;

    private StorageService() {
        s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(Storage.getSpaceEndpoint(), Storage.getSpaceRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(Storage.getSpaceSecret(), Storage.getSpaceKey()))).build();
    }

    public static StorageService getInstance() {
        if (s3Client == null) {
            return storageService = new StorageService();
        } else {
            return storageService;
        }
    }

    private static AmazonS3 getConnection() {
        return s3Client;
    }

    public static boolean add(Storage storage) throws ClassNotFoundException, SQLException {
        if (StorageService.getInstance() != null) {
            PutObjectRequest request = new PutObjectRequest(Storage.getSpaceName(), storage.getFilePath(), new File("localPathToFile"));
            StorageService.getConnection().putObject(request);
            return true;
        }
        return false;
    }

    public static boolean remove(Storage storage) throws ClassNotFoundException, SQLException {
        if (StorageService.getInstance() != null) {
            DeleteObjectRequest request = new DeleteObjectRequest(Storage.getSpaceName(), storage.getFilePath());
            StorageService.getConnection().deleteObject(request);
            return true;
        }
        return false;
    }

    public static URL get(Storage storage) throws ClassNotFoundException, SQLException {
        if (StorageService.getInstance() != null) {
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(Storage.getSpaceName(), storage.getFilePath()).withMethod(HttpMethod.GET);
            return s3Client.generatePresignedUrl(request);
        }
        return null;
    }
}
