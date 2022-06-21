//package starter.Oupatient;
//
//import net.serenitybdd.rest.SerenityRest;
//import org.json.simple.JSONObject;
//
//public class CreateOutpatient {
//    String base_url = "http://ec2-3-91-177-221.compute-1.amazonaws.com/";
//
//    public String endpointLogin(){
//        return base_url +"api/outpatient";
//    }
//
//    public void requestPostLogin(String email, String password){
//        JSONObject requestData = new JSONObject();
//        requestData.put("email",email);
//        requestData.put("password", password);
//
//        SerenityRest.given().header("Content-Type", "application/json")
//                .body(requestData.toJSONString());
//        SerenityRest.when().post(endpointLogin());
//    }
//
//}
