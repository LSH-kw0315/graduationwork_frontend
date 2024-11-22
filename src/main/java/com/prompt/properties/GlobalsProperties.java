package com.prompt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "globals")
public class GlobalsProperties {
	public String methodGet = "get";
	public String methodPost = "post";

	public String doHome;
	public String doLogin;
	public Boolean dupLoginYn;

	public String fileSaveWebRoot;
	public String fileWebrootPath;
	public String filePath;
	public String imgFileMaxSize;
	public String fileMaxSize;
	public String videoFileMaxSize;

}
