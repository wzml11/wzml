package com.wzml.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

//@Component
//public class AliyunOSSOperator {
//
//    private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//    private String bucketName = "wzml-java-01";
//    private String region = "cn-beijing";
//
//    public String upload(byte[] content, String originalFilename) throws Exception {
//        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//
//        // 填写Object完整路径，例如202406/1.png。Object完整路径中不能包含Bucket名称。
//        //获取当前系统日期的字符串,格式为 yyyy/MM
//        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
//        //生成一个新的不重复的文件名
//        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        String objectName = dir + "/" + newFileName;
//
//        // 创建OSSClient实例。
//        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//        OSS ossClient = OSSClientBuilder.create()
//                .endpoint(endpoint)
//                .credentialsProvider(credentialsProvider)
//                .clientConfiguration(clientBuilderConfiguration)
//                .region(region)
//                .build();
//
//        try {
//            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
//        } finally {
//            ossClient.shutdown();
//        }
//
//        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
//    }
//
//}
@Component
public class AliyunOSSOperator {

    @Value("${aliyun.oss.endpoint:https://oss-cn-beijing.aliyuncs.com}")
    private String endpoint;
    
    @Value("${aliyun.oss.bucket-name:wzml-java-01}")
    private String bucketName;
    
    @Value("${aliyun.oss.region:cn-beijing}")
    private String region;
    
    @Value("${aliyun.oss.access-key-id:}")
    private String accessKeyId;
    
    @Value("${aliyun.oss.access-key-secret:}")
    private String accessKeySecret;

    public String upload(byte[] content, String originalFilename) throws Exception {
        // 优先使用配置文件中的密钥，如果没有则使用环境变量
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        
        OSS ossClient;
        if (accessKeyId != null && !accessKeyId.isEmpty() && accessKeySecret != null && !accessKeySecret.isEmpty()) {
            // 使用配置文件中的凭证
            DefaultCredentialProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
            ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(credentialsProvider)
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();
        } else {
            // 使用环境变量中的凭证
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
            ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(credentialsProvider)
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();
        }

        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } finally {
            ossClient.shutdown();
        }

        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }

}
