package com.netsense.cloudfilemanager.xskybasic;

import com.netsense.cloudfilemanager.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @Auther: rui
 * @Date: 2018/8/29 15:02
 * @Description:
 */

public class Xskythread {

    private static Logger logger = LoggerFactory.getLogger(Xskythread.class);

    public static String akey = PropertiesUtil.getPropertieValue("common.properties","akey");
    public static String skey = PropertiesUtil.getPropertieValue("common.properties","skey");
    public static String endpoint = PropertiesUtil.getPropertieValue("common.properties","endpoint");

//    public static String akey = "CLZEBCF30YFGY46ES2OO";
//    public static String skey = "x6JTSojiWh39bO7SJfJ5eh0a0PeZJgvFCFhN6SJ7";
//    public static String endpoint = "http://bjgc3-gos.gomecloud.com:80";

    public static void init() {
        try {
            //init
            if(xskyS3Basic.client == null) {
                if (!xskyS3Basic.init_s3_lib(endpoint, akey, skey)) {
                    System.out.println("init s3 lib success!\n");
                    logger.error("init s3 lib failed!\n");
                    return;
                }
            }
            logger.info("init s3 lib success!\n");
        } catch (Exception e) {
            logger.error("xsky init fail", e);
        }
    }


    //删除云上文件
    public static void deletefile(String bucket, String key) {
        try {
            xskyS3Basic.DeletingAnObject(bucket, key);
            xskyS3Basic.DeletingBucket(bucket);
            logger.info("deletefromYun:" + bucket + ":" + key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
