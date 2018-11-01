package com.netsense.cloudfilemanager.xskybasic;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.netsense.cloudfilemanager.utils.PropertiesUtil;

import java.io.*;

public class xskyS3test {

    public static String bucketName = "test00";
    public static String file_path = "Test/mysql-5.7.18-macos10.12-x86_64.dmg";
    // public static String file_path="/home/search/master.zip";

    public static String akey = PropertiesUtil.getPropertieValue("common.properties","akey");
    public static String skey = PropertiesUtil.getPropertieValue("common.properties","skey");
    public static String endpoint = PropertiesUtil.getPropertieValue("common.properties","endpoint");

    public static void main(String[] args) {
        try {
        	// Init s3 lib
        	if (!xskyS3Basic.init_s3_lib(endpoint, akey, skey)) {
        		System.out.println("init s3 lib failed!\n");
        		return;
        	}

            // Create Bucket
            if (!xskyS3Basic.HasBucket(bucketName)) {
            	xskyS3Basic.CreatingBucket(bucketName);
                System.out.println("Successful create bucket: " + bucketName + "\n");
            } else {
                System.out.println("Bucket " + bucketName + " is exists!\n");
            }
            
            // Write a file
/*            System.out.println(System.currentTimeMillis()+"---------");
            File file = new File(file_path);
            xskyS3Basic.WritingAnObject(bucketName, file_path, file);
            System.out.println("Successful put object: " + file_path + "\n");
            System.out.println(System.currentTimeMillis()+"-----------");*/

            // List files
/*            System.out.println("List objects of bucket(" + bucketName + "): ");
            List<S3ObjectSummary> obj_list = xskyS3Basic.ListingObjects(bucketName);
            for (S3ObjectSummary obj_summary : obj_list) {
            	System.out.println("    " + obj_summary.getKey());
            }*/

            
            // Read a file
/*            System.out.println(System.currentTimeMillis()+"===========");
            S3Object s3object = xskyS3Basic.ReadingAnObject(bucketName, file_path);
            System.out.println("\nGet object: " + file_path);
            displayTextInputStream(s3object.getObjectContent());
            System.out.println(System.currentTimeMillis()+"===========");*/

            //download file
/*            System.out.println(System.currentTimeMillis()+"--------");
            ObjectMetadata objectMetadata = xskyS3Basic.DownloadObject("Test/a/mysql-5.7.18-macos10.12-x86_64.dmg",bucketName, file_path);
            System.out.println(objectMetadata.getContentLength()+"===========");
            System.out.println(System.currentTimeMillis()+"--------");*/




            // Delete Object
            xskyS3Basic.DeletingAnObject("aaa", "bbb");

            // Delete Bucket
            xskyS3Basic.DeletingBucket(bucketName);
            
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which" + " means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means" + " the client encountered "
                    + "an internal error while trying to " + "communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    private static void displayTextInputStream(InputStream input) throws IOException {

        String path = "Test/a/test1.txt";
        File file = new File(path);

//        int index;
//        byte[] bytes = new byte[1024];
//        FileOutputStream downloadFile = new FileOutputStream(path);
//        while ((index = input.read(bytes)) != -1) {
//            downloadFile.write(bytes, 0, index);
//            downloadFile.flush();
//        }
//        downloadFile.close();
//        input.close();



        // Read one text line at a time and display.
//        OutputStream out = new FileOutputStream("Test/a/mysql-5.7.18-macos10.12-x86_64.dmg");

        BufferedInputStream in = new BufferedInputStream(input);

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("Test/a/mysql-5.7.18-macos10.12-x86_64.dmg"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        byte[] bytes = new byte[1024];
        int len = -1;

        while ((len = in.read(bytes,0,bytes.length)) != -1) {
            //写入相关文件
            out.write(bytes, 0, len);
//            writer.newLine();
        }

        out.flush();
        input.close();
        out.close();
    }
}
