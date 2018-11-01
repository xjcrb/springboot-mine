package com.netsense.cloudfilemanager.xskybasic;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class xskyS3Basic {
	
	public static AmazonS3 client = null;
	
	public static Boolean init_s3_lib(String server_url, String access_key, String secret_key) {
		
		if ( null != client )
        {
            return false;
        }
		
		AWSCredentials credentials = null;
        try {
            credentials = new BasicAWSCredentials(access_key, secret_key);
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
        
        //��ʼ��S3 config ��ʵ��
        ClientConfiguration config = new ClientConfiguration();
        config.setProtocol(Protocol.HTTP);
        config.setConnectionTimeout(30000);
        config.setUseExpectContinue(false);
        
        client = new AmazonS3Client(credentials, config);
        client.setEndpoint(server_url);
        client.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
        
        if ( null == client )
        {
            return false;
        }

        return true;
    }
	
	public static List<Bucket> ListingBuckets() {
		 
		return client.listBuckets();
	}
	
	
	public static Boolean HasBucket(String bucketName) {
		 
		return client.doesBucketExist(bucketName);
	}
	
	public static Bucket CreatingBucket(String bucketName)
    {
        return client.createBucket(bucketName);
    }
	
	public static void DeletingBucket(String bucketName)
    {
        client.deleteBucket(bucketName);
    }
	
	public static List<S3ObjectSummary> ListingObjects(String bucketName)
    {
        ListObjectsV2Result objects = client.listObjectsV2(bucketName);
        
        return objects.getObjectSummaries();
    }
	
	public static PutObjectResult WritingAnObject(String bucketName, String key, File file)
    {
		return client.putObject(new PutObjectRequest(bucketName, key, file));
    }

    public static PutObjectResult WritingbyteInputstreamObject(String bucketName, InputStream input, File file)
    {
        return client.putObject(bucketName, file.getName(),input, new ObjectMetadata());
    }
	
	public static S3Object ReadingAnObject(String bucketName, String key)
	{
		return client.getObject(new GetObjectRequest(bucketName, key));
	}

    public static ObjectMetadata DownloadObject(String path, String bucketName, String key)
    {
        return  client.getObject(new GetObjectRequest(bucketName, key),new File(path));
    }

    public static URL GeneratePresignedUrlRequest(String bucketName, String key)
    {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key);
        return  client.generatePresignedUrl(request);
    }
	
	public static void DeletingAnObject(String bucketName, String key)
	{
		client.deleteObject(bucketName, key);
	}
}
