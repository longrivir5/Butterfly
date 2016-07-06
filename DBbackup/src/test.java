import com.google.gson.Gson;
import com.json.PassportJson;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * String reg = "(^[0-9]{1}.[0-9]{1})"; Pattern pattern =
		 * Pattern.compile(reg); Matcher matcher = pattern.matcher("1..1abccc");
		 * 
		 * //System.out.println(matcher.find());
		 * 
		 * String a = "FAYEA4AHJA@TES255411622T.COM.TW222212";
		 * System.out.println(a.length());
		 */

		String testjson = "{'IsSucces':true,'Msg':'жие\','Data':[{'UserID':'Apple','ClientIP':'10.112.3.7'},{'UserID':'orange','ClientIP':'10.115.3.7'}]}";
		Gson gson = new Gson();
		PassportJson pj = gson.fromJson(testjson, PassportJson.class);
		
		
		
	}

}
