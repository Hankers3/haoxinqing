package com.aebiz.b2b2c.cms.platforminfo.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.exception.LogException;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.AbortMultipartUploadRequest;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.CannedAccessControlList;
import com.baidubce.services.bos.model.CompleteMultipartUploadRequest;
import com.baidubce.services.bos.model.CompleteMultipartUploadResponse;
import com.baidubce.services.bos.model.InitiateMultipartUploadRequest;
import com.baidubce.services.bos.model.InitiateMultipartUploadResponse;
import com.baidubce.services.bos.model.ListMultipartUploadsRequest;
import com.baidubce.services.bos.model.ListMultipartUploadsResponse;
import com.baidubce.services.bos.model.MultipartUploadSummary;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PartETag;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.services.bos.model.UploadPartRequest;
import com.baidubce.services.bos.model.UploadPartResponse;
import com.baidubce.services.media.MediaClient;
import com.baidubce.services.media.model.CreatePipelineResponse;
import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.GetMediaInfoOfFileResponse;
import com.baidubce.services.media.model.GetPipelineResponse;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.Job;
import com.baidubce.services.media.model.ListPresetsResponse;

@Component
public class PlatFormInfoHelper {
	
	/**
	 * 上传视频获取返回的连接
	 * @param file
	 * @param fileName
	 * @return
	 */
	public String uploadFile(MultipartFile file, String fileName){
		String returnUrl = "";
		try {
			BosClient client = createClient();
			String bucketName = MessageHelper.getMessage("bos.bucketName");
			if(!StringUtil.isEmpty(bucketName)){
				if(!doesBucketExist(client,bucketName)){
					createBucket(client,bucketName);
				}else{
					PutObject(client,bucketName,fileName,file.getInputStream());
					
					returnUrl = generatePresignedUrl(fileName);
				}
			}
		}catch (Exception e) {
		  throw new LogException("file.upload", "uploadFile1 error", e);
		}
		return returnUrl;
	}
	
	
	/**
	 * 新建bucket
	 * @param client
	 * @param bucketName
	 */
	public void createBucket (BosClient client, String bucketName) {
	    client.createBucket(bucketName);
	}
	/**
	 * 新建bosClient
	 * @return
	 */
	public static BosClient createClient(){
		String ACCESS_KEY_ID = MessageHelper.getMessage("bos.access.key.id");                   // 用户的Access Key ID
	    String SECRET_ACCESS_KEY = MessageHelper.getMessage("bos.secret.access.key");           // 用户的Secret Access Key
	    String ENDPOINT = MessageHelper.getMessage("bos.endpoint");     // 该bucket所在区域的endpoint
	    // 初始化一个BosClient
	    BosClientConfiguration config = new BosClientConfiguration();
	    config.setEndpoint(ENDPOINT);
	    config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
	    BosClient client = new BosClient(config);
	    return client;
	}
	/**
	 * 判断bucket是否存在
	 * @param client
	 * @param bucketName
	 * @return
	 */
	public boolean doesBucketExist (BosClient client, String bucketName) {
	    boolean exists = client.doesBucketExist(bucketName);                //指定Bucket名称
	    return exists;
	}
	/**
	 * 设置bucket权限
	 * @param client
	 * @param bucketName
	 */
	public static void setBucketPrivate (BosClient client, String bucketName,CannedAccessControlList acl) {
	    client.setBucketAcl(bucketName, acl);
	}
	/**
	 * 上传文件
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @throws FileNotFoundException
	 */
	public void PutObject(BosClient client, String bucketName, String objectKey, InputStream inputStream) throws FileNotFoundException{
	    // 获取指定文件
	    //File file = new File("/path/to/file.zip");
	    // 获取数据流
	    //InputStream inputStream = new FileInputStream("F:/713ad15bc06c74cf62d690b866855d13242632.mp4");
	    // 以文件形式上传Object
	    //PutObjectResponse putObjectFromFileResponse = client.putObject(bucketName, objectKey, file);
	    // 以数据流形式上传Object
	    PutObjectResponse putObjectResponseFromInputStream = client.putObject(bucketName, objectKey, inputStream);
	    // 以二进制串上传Object
	    //PutObjectResponse putObjectResponseFromByte = client.putObject(bucketName, objectKey, byte1);
	    // 以字符串上传Object
	    //PutObjectResponse putObjectResponseFromString = client.putObject(bucketName, objectKey, string1);
	    // 打印ETag
	    //System.out.println(putObjectFromFileResponse.getETag());
	    //System.out.println(putObjectResponseFromInputStream.getETag());
	}
	/**
	 * 生成链接
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param expirationInSeconds
	 * @return
	 */
	public static String generatePresignedUrl(String objectKey) {
		BosClient client = createClient();
		String bucketName = MessageHelper.getMessage("bos.bucketName");
		String expirationInSecondsStr = MessageHelper.getMessage("bos.secret.access.key");
		int expirationInSeconds = StringUtil.getInt(expirationInSecondsStr);
		if(expirationInSeconds==0)expirationInSeconds=1800;
		URL url = client.generatePresignedUrl(bucketName, objectKey, expirationInSeconds);   
		//指定用户需要获取的Object所在的Bucket名称、该Object名称、时间戳、URL的有效时长   
		return url.toString();
	}
	
	public static void main(String[] args) {
		PlatFormInfoHelper p  = new PlatFormInfoHelper();
		System.out.println( p.generatePresignedUrl("shopLevelIcon20151207160625.mp4"));;
		//Player player =  Manager.createRealizedPlayer(p);
		//player.getDuration().getSeconds();
	}
	
	/**
	 * 新建一个MediaClient
	 */
	public static MediaClient createMediaClient(){
		String ACCESS_KEY_ID = MessageHelper.getMessage("bos.access.key.id");
	    String SECRET_ACCESS_KEY = MessageHelper.getMessage("bos.secret.access.key");
	    String ENDPOINT = "http://media.bj.baidubce.com";

	    // 初始化一个MediaClient
	    BceClientConfiguration config = new BceClientConfiguration();
	    config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
	    config.setEndpoint(ENDPOINT);
	    MediaClient client = new MediaClient(config);
	    return client;
	}
	
	/**
	 * 根据配置新建一个MediaClient
	 */
	public static MediaClient createMediaClientSetBceClientConfiguration(){
		//String ACCESS_KEY_ID = SystemManager.getValueByKey("baidu.access_key_id");
	    //String SECRET_ACCESS_KEY = SystemManager.getValueByKey("baidu.secret_access_key");
	    
	    String ACCESS_KEY_ID = MessageHelper.getMessage("bos.access.key.id");
	    String SECRET_ACCESS_KEY = MessageHelper.getMessage("bos.secret.access.key");
		String ENDPOINT = "http://media.bj.baidubce.com";

		// 创建BceClientConfiguration实例
		BceClientConfiguration config = new BceClientConfiguration();

		// 配置代理为本地8080端口
		config.setProxyHost("127.0.0.1");
		config.setProxyPort(8080);
		
		//设置用户名和密码
		config.setProxyUsername("username");
		config.setProxyPassword("password");
		
		// 设置HTTP最大连接数为10
		config.setMaxConnections(10);
		
		// 设置TCP连接超时为5000毫秒
		config.setConnectionTimeoutInMillis(5000);

		// 设置Socket传输数据超时的时间为2000毫秒
		config.setSocketTimeoutInMillis(2000);

		// 配置认证秘钥和服务器信息
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID,SECRET_ACCESS_KEY));
		config.setEndpoint(ENDPOINT);

		// 创建Media客户端
		MediaClient client = new MediaClient(config);
		return client;
	}
	
	/**
	 * 新建一个队列Pipeline，默认的capacity值为20：
	 * @param client
	 * @param pipelineName
	 * @param sourceBucket
	 * @param targetBucket
	 * @param capacity
	 */
	public static CreatePipelineResponse createPipeline (MediaClient client, String pipelineName,
	    String sourceBucket, String targetBucket, int capacity) {
	    // 新建一个Pipeline
		CreatePipelineResponse pipeline = client.createPipeline(pipelineName, sourceBucket, targetBucket, capacity);
		return pipeline;
	}

	/**
	 * 根据MediaClient与队列名获取队列对象
	 * @param client
	 * @param pipelineName
	 */
	public static GetPipelineResponse getPipeline (MediaClient client, String pipelineName) {
	    GetPipelineResponse pipeline = client.getPipeline(pipelineName);
	    System.out.println(pipeline);
	    return pipeline;
	}
	
	/**
	 * 创建转码任务返回任务id
	 * @param client
	 * @param pipelineName
	 * @param sourceKey
	 * @param targetKey
	 * @param presetName
	 */
	public static String createJob(MediaClient client, String pipelineName,
	          String sourceKey, String targetKey, String presetName) {
	    String jobId = client.createJob(pipelineName, sourceKey, targetKey, presetName).getJobId();
	    return jobId;
	}

	/**
	 * 通过指定队列名pipelineName查询该队列Pipeline下的所有Job
	 * @param client
	 * @param pipelineName
	 */
	public static List<Job> listJobs(MediaClient client, String pipelineName) {
	    // 获取指定Pipeline下的所有Job信息
	    List<Job> jobs = client.listJobs(pipelineName).getJobs();
	    for (Job job : jobs) {
	        System.out.println("jobId: " + job.getJobId());
	    }
	    return jobs;
	}
	/**
	 * 获取转码任务信息
	 * @param client
	 * @param jobId
	 */
	public static void getJob(MediaClient client, String jobId) {
	    GetJobResponse resp = client.getJob(jobId);
	    System.out.println("pipelineName: " + resp.getPipelineName());
	    System.out.println("sourceKey: " + resp.getSource().getSourceKey());
	    System.out.println("targetKey: " + resp.getTarget().getTargetKey());
	    System.out.println("jobStatus: " + resp.getJobStatus());
	    System.out.println("startTime: " + resp.getStartTime());
	    System.out.println("endTime: " + resp.getEndTime());
	}

	/**
	 * 查询所有的模版Preset
	 * @param client
	 */
	public static ListPresetsResponse listPresets(MediaClient client) {
	    ListPresetsResponse resp = client.listPresets();
	    for (GetPresetResponse preset : resp.getPresets()) {
	        System.out.println("presetName: " + preset.getPresetName());
	        System.out.println("description: " + preset.getDescription());
	        System.out.println("container: " + preset.getContainer());
	        System.out.println("transmux: " + preset.getTransmux());
	    }
	    return resp;
	}
	
	/**
	 * 查询指定的模版Preset信息
	 * @param client
	 * @param presetName
	 */
	public static GetPresetResponse getPreset(MediaClient client, String presetName) {
	    GetPresetResponse preset = client.getPreset(presetName);
	    System.out.println("presetName: " + preset.getPresetName());
	    System.out.println("description: " + preset.getDescription());
	    System.out.println("container: " + preset.getContainer());
	    System.out.println("transmux: " + preset.getTransmux());
	    return preset;
	}

	/**
	 * 创建仅支持容器格式转换的模版Preset
	 * @param client
	 * @param presetName
	 * @param description
	 * @param container
	 */
	public static void createPreset(MediaClient client, String presetName, String description, String container) {
	    client.createPreset(presetName, description, container);
	}


	
	/**
	 * 获取媒体信息Mediainfo
	 * @param client
	 * @param bucket
	 * @param key
	 */
	public static GetMediaInfoOfFileResponse getMediaInfoOfFile(MediaClient client, String bucket, String key) {
	    GetMediaInfoOfFileResponse mediaInfo = client.getMediaInfoOfFile(bucket, key);
	    System.out.println("fileSizeInByte: " + mediaInfo.getFileSizeInByte());
	    System.out.println("etag: " + mediaInfo.getEtag());
	    System.out.println("type: " + mediaInfo.getType());
	    return mediaInfo;
	}
	
	/**
	 * 删除上传文件
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 */
	public static void deleteObject(BosClient client, String bucketName, String objectKey) {
		//指定要删除的Object所在Bucket名称和该Object名称
		client.deleteObject(bucketName, objectKey);
	}
	
	/**
	 * 删除已经上传到百度云的文件
	 * @param fileName
	 */
	public  void deleteFile(String fileName){
		//新建bosClient
		BosClient bClient = createClient();
		
		//源桶名称
		String sourceBucketName = MessageHelper.getMessage("bos.bucketName");
		deleteObject(bClient, sourceBucketName, fileName);
	}

	
	/**
	 * 上传成功后进行转码任务并返回编码后文件名称
	 * @return
	 */
	public  static String transcodingMedia(String fileName,String randomKey){
		//新建bosClient
		BosClient bClient = createClient();
		//创建mediaclient
		MediaClient mClient = createMediaClient();
		
		//队列名称
		String pipelineName =MessageHelper.getMessage("baidu.pipeline_name");
		//源桶名称
		String sourceBucketName = MessageHelper.getMessage("bos.bucketName");
		//目标桶名称
		String targetBucketName = MessageHelper.getMessage("bos.bucketName.target");
		//队列任务量
		int capacity = 20;
		//模板名称
		String presetName = MessageHelper.getMessage("baidu.preset_name");
		//模板描述
		String description = MessageHelper.getMessage("baidu.preset_description");
		//容器
		String container = "";
		
		//根据MediaClient与队列名获取队列对象,如果不存在则创建。
		GetPipelineResponse pipe = getPipeline(mClient, pipelineName);
		if (pipe==null) {
			//创建队列Pipeline
			CreatePipelineResponse pipeline = createPipeline(mClient, pipelineName, sourceBucketName, targetBucketName, capacity);
		}
		
		
		//创建模板
		//createPreset(mClient, presetName, description, container);
		
		//原始音视频资源的BOS Key【输入文件名】
		String sourceKey = fileName;
		//目标音视频资源BOS Key【输出文件名】
		String name = fileName.substring(0,fileName.lastIndexOf("."));
		String targetKey = randomKey + "_"+ name+"_transcoding.mp4";
		//创建转码任务
		String jobId = createJob(mClient, pipelineName, sourceKey, targetKey, presetName);
		if (!StringUtil.isEmpty(jobId)) {
			return targetKey+","+jobId;
		}else{
			return sourceKey;
		}
		
	}
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 分块上传 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	/**
	 * 初始化一个分块上传事件 返回uploadId
	 */
	public static String initiateMultipartUpload(BosClient client,String bucketName, String objectKey){
		// 开始Multipart Upload
		InitiateMultipartUploadRequest initiateMultipartUploadRequest =  new InitiateMultipartUploadRequest(bucketName, objectKey);
		InitiateMultipartUploadResponse initiateMultipartUploadResponse = client.initiateMultipartUpload(initiateMultipartUploadRequest);
		String uploadId = "";
		uploadId = initiateMultipartUploadResponse.getUploadId();
		
		// 打印UploadId
		System.out.println("UploadId: " + initiateMultipartUploadResponse.getUploadId());
		return uploadId;
	}
	
	/**
	 * 分块上传
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param uploadId
	 * @param file
	 * @return
	 */
	public static List<PartETag> multipartUpload(BosClient client,String bucketName, String objectKey,String uploadId,MultipartFile file){
		// 设置每块为 5MB
		final long partSize = 1024 * 1024 * 5L;

		//File partFile = new File("/path/to/file.zip");
		int fileSize = (int) file.getSize();
		//int fileSize = (int) file.length();

		// 计算分块数目
		int partCount = (int) (fileSize / partSize);
		if (fileSize % partSize != 0){
		    partCount++;
		}

		// 新建一个List保存每个分块上传后的ETag和PartNumber
		List<PartETag> partETags = new ArrayList<PartETag>();

		for(int i = 0; i < partCount; i++){
			try {
				 // 获取文件流
			    FileInputStream fis = (FileInputStream) file.getInputStream(); 
				//FileInputStream fis = new FileInputStream(file);

			    // 跳到每个分块的开头
			    long skipBytes = partSize * i;
			    fis.skip(skipBytes);

			    // 计算每个分块的大小
			    long size = partSize < fileSize - skipBytes ?
			            partSize : fileSize - skipBytes;

			    // 创建UploadPartRequest，上传分块
			    UploadPartRequest uploadPartRequest = new UploadPartRequest();
			    uploadPartRequest.setBucketName(bucketName);
			    uploadPartRequest.setKey(objectKey);
			    uploadPartRequest.setUploadId(uploadId);
			    uploadPartRequest.setInputStream(fis);
			    uploadPartRequest.setPartSize(size);
			    uploadPartRequest.setPartNumber(i + 1);
			    UploadPartResponse uploadPartResponse = client.uploadPart(uploadPartRequest);

			    // 将返回的PartETag保存到List中。
			    partETags.add(uploadPartResponse.getPartETag());

			    // 关闭文件
			    fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return partETags;

	}
	
	/**
	 * 完成分块上传
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param uploadId
	 * @param partETags
	 */
	public static String completeUpload(BosClient client,String bucketName, String objectKey,String uploadId,List<PartETag> partETags){
		CompleteMultipartUploadRequest completeMultipartUploadRequest =  new CompleteMultipartUploadRequest(bucketName, objectKey, uploadId, partETags);
	
		// 完成分块上传
		CompleteMultipartUploadResponse completeMultipartUploadResponse = client.completeMultipartUpload(completeMultipartUploadRequest);
	
		// 打印Object的ETag
		System.out.println(completeMultipartUploadResponse.getETag());
		if (!StringUtil.isEmpty(completeMultipartUploadResponse.getETag())) {
			return "true";
		}else{
			return "false";
		}

	}
	
	/**
	 * 取消分块上传
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 * @param uploadId
	 */
	public static void cancelMultipartUpload(BosClient client,String bucketName, String objectKey,String uploadId){
		AbortMultipartUploadRequest abortMultipartUploadRequest = new AbortMultipartUploadRequest(bucketName, objectKey, uploadId);

		// 取消分块上传
		client.abortMultipartUpload(abortMultipartUploadRequest);
	}
	
	/**
	 * 获取未完成的分块上传事件
	 * @param client
	 * @param bucketName
	 */
	public static void getListMultipartUploads(BosClient client,String bucketName){
		ListMultipartUploadsRequest listMultipartUploadsRequest = new ListMultipartUploadsRequest(bucketName);

		// 获取Bucket内所有上传事件
		ListMultipartUploadsResponse listing = client.listMultipartUploads(listMultipartUploadsRequest);

		// 遍历所有上传事件
		for (MultipartUploadSummary multipartUpload : listing.getMultipartUploads()) {
		    System.out.println("Key: " + multipartUpload.getKey() + " UploadId: " + multipartUpload.getUploadId());
		}
	}

	
	//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑分块上传↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	/**
	 * 只获取ObjectMetadata不获取其实体
	 * @param client
	 * @param bucketName
	 * @param objectKey
	 */
	public static ObjectMetadata getObjectMetadata (BosClient client,String bucketName,String objectKey){
		ObjectMetadata objectMetadata = null;
		objectMetadata = client.getObjectMetadata(bucketName, objectKey);
		BosObject object = client.getObject(bucketName, objectKey);
		if (objectMetadata != null) {
			System.out.println(objectMetadata.getUserMetadata());
			return objectMetadata; 
		}else{
			return new ObjectMetadata();
		}
		
	}
	
}
