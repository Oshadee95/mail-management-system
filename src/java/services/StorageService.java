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
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import configurations.StorageConfig;
import java.io.IOException;
import java.security.UnrecoverableKeyException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author RED-HAWK
 */
public class StorageService {

    private static StorageService storageService;
    private static AmazonS3 s3Client;

    private StorageService() throws UnrecoverableKeyException {
        s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(StorageConfig.getSpaceEndpoint(), StorageConfig.getSpaceRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(StorageConfig.getSpaceKey(), StorageConfig.getSpaceSecret()))).build();
    }

    public static StorageService getInstance() throws UnrecoverableKeyException {
        if (s3Client == null) {
            return storageService = new StorageService();
        } else {
            return storageService;
        }
    }

    private static AmazonS3 getConnection() {
        return s3Client;
    }

    public static boolean add(StorageConfig storage) throws ClassNotFoundException, SQLException, UnrecoverableKeyException {
        if (StorageService.getInstance() != null) {
            PutObjectRequest request = new PutObjectRequest(StorageConfig.getSpaceName(), storage.getFilePath(), new File(storage.getPathToLocalFile()));
            StorageService.getConnection().putObject(request);
            return true;
        }
        return false;
    }

    public static boolean remove(StorageConfig storage) throws ClassNotFoundException, SQLException, UnrecoverableKeyException {
        if (StorageService.getInstance() != null) {
            DeleteObjectRequest request = new DeleteObjectRequest(StorageConfig.getSpaceName(), storage.getFilePath());
            StorageService.getConnection().deleteObject(request);
            return true;
        }
        return false;
    }

    public static URL get(StorageConfig storage) throws ClassNotFoundException, SQLException, UnrecoverableKeyException {
        if (StorageService.getInstance() != null) {
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(StorageConfig.getSpaceName(), storage.getFilePath()).withMethod(HttpMethod.GET);
            return s3Client.generatePresignedUrl(request);
        }
        return null;
    }

    public static boolean download(StorageConfig storage) throws IOException, UnrecoverableKeyException {
        if (StorageService.getInstance() != null) {
            S3ObjectInputStream inputStream = StorageService.getConnection().getObject(StorageConfig.getSpaceName(), storage.getFilePath()).getObjectContent();
            File isImageSaved = new File(storage.getPathToLocalFile());
            FileUtils.copyInputStreamToFile(inputStream, isImageSaved);
            return isImageSaved.exists();
        }
        return false;
    }

    public static boolean isFileAvailable(StorageConfig storage) throws UnrecoverableKeyException {
        if (StorageService.getInstance() != null) {
            return StorageService.getConnection().doesObjectExist(StorageConfig.getSpaceName(), storage.getFilePath());
        } else {
            return false;
        }
    }

}
