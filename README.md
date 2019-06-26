# Reading Terraform State File

## 1. Build jar 

The folowing code creates jar file
```
  mvn package
```

## 2. Using jar file in Jenkinsfile 

Add this jar to classpath and then use following code to invoke method

```code()

import in.sai.terraform.ParseTFState;

node{
  stage('Parse Terraform State'){
        def sourceFilePath = "<your location of terraform state file>"
	def destFilePath = "<your location where you want output path>"
  	ParseTFState.readTerraformStateFile(sourceFilePath,destFilePath);
  }

}	

```
