//package mapper;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//
//
//import java.util.Map;
//
//public class Mapper {
//    public static void main(String[] args) throws Exception {
//        var objectMapper = new ObjectMapper();
//        var ref = new TypeReference<Map<String, String>>() {};
//        System.out.println(Mapper.class.getClassLoader().getResource("main.fxml"));
//        Parent root = FXMLLoader.load(Mapper.class.getClassLoader().getResource("main.fxml"));
//        boolean map = false;
//        System.out.println(map);
//    }
//
//
//
//}
