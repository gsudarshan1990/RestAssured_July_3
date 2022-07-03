package res;

import org.serial.CreateIssue;
import org.serial.Fields;
import org.serial.IssueType;
import org.serial.Project;
import org.serial.UpdateIssueFields;
import org.serial.UpdateRoot;

public class PayLoad {

	public static String createUser() {
		return "{\r\n" + "    \"name\": \"Manoj\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";

	}

	public static String updateUser() {
		return "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"Manager\"\r\n" + "}";

	}
	public static String updateUser(String name) {
		return "{\r\n" + "    \"name\": \""+name+"\",\r\n" + "    \"job\": \"Manager\"\r\n" + "}";
		
	}

	public static String createIssue() {
		return "{\r\n" + "    \"fields\": {\r\n" + "       \"project\":\r\n" + "       {\r\n"
				+ "          \"key\": \"SG\"\r\n" + "       },\r\n"
				+ "       \"summary\": \"Login Not working in Swiggy\",\r\n"
				+ "       \"description\": \"User cant able to login in app\",\r\n" + "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n" + "       }\r\n" + "   }\r\n" + "}";

	}
	public static CreateIssue issueCreate() {
		Project project= new  Project();
		project.setKey("SG");
		IssueType issuetype= new  IssueType();
		issuetype.setName("Bug");
		
		Fields fields = new Fields();
		fields.setProject(project);
		fields.setSummary("Payment Not Done");
		fields.setDescription("Credit card not working");
		fields.setIssuetype(issuetype);
		
		CreateIssue createIssue= new CreateIssue();
		createIssue.setFields(fields);
		return createIssue;
		
	}
	public static CreateIssue issueCreate(String sum,String des) {
		Project project= new  Project();
		project.setKey("SG");
		IssueType issuetype= new  IssueType();
		issuetype.setName("Bug");
		
		Fields fields = new Fields();
		fields.setProject(project);
		fields.setSummary(sum);
		fields.setDescription(des);
		fields.setIssuetype(issuetype);
		
		CreateIssue createIssue= new CreateIssue();
		createIssue.setFields(fields);
		return createIssue;
		
	}

	public static String updateIssue() {
		return "{\r\n" + "    \"fields\" : {\r\n" + "        \"summary\": \"Login in Swiggy not working\",\r\n"
				+ "        \"description\": \"user cant login with mobile number\"\r\n" + "    }\r\n" + "}";

	}
	public static UpdateRoot issueUpdate() {
		UpdateIssueFields issueFileds= new UpdateIssueFields();
		issueFileds.setSummary("Payment still not working");
		issueFileds.setDescription("Debit card cant done");
		
		UpdateRoot rd= new UpdateRoot();
		rd.setFields(issueFileds);
		return rd;
	}
	public static UpdateRoot issueUpdate(String s,String ds) {
		UpdateIssueFields issueFileds= new UpdateIssueFields();
		issueFileds.setSummary(s);
		issueFileds.setDescription(ds);
		
		UpdateRoot rd= new UpdateRoot();
		rd.setFields(issueFileds);
		return rd;
	}

}
