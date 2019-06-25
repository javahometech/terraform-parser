package in.sai.terraform;

public class TerraformType {
	String type;
	String id;
	public TerraformType() {
		
	}
	public TerraformType(String type, String id) {
		super();
		this.type = type;
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "{type=" + type + ", id=" + id + "}";
	}
	
}
