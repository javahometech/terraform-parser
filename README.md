# Reading Terraform State File

## 1. Build jar 

The folowing code creates jar file
```
  mvn package
```

## 2. Using jar file 

Add this jar to classpath and then use following code to invoke method

```

import in.sai.terraform.ParseTFState;

public class Main {
public static void main(String[] args) {
	
	ParseTFState.readTerraformStateFile("terraform.json","output.json");
}
}

```
