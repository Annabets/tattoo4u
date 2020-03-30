package by.bsuir.tattoo4u.dto.request;

import by.bsuir.tattoo4u.entity.Studio;
import lombok.Data;
import lombok.EqualsAndHashCode;

//import by.bsuir.tattoo4u.entity.Contact;


@Data
@EqualsAndHashCode()
public class StudioRegistrationRequestDto {
    //private final static Gson gson = new Gson();

    private String name;
    private String description;
    private String address;
    private String contact;



    public Studio getStudio() {
        return new Studio(name, description, address, contact);
    }
//
//    public static StudioRegistrationRequestDto fromJson(String jsonString){
//        if(jsonString==null || jsonString.isEmpty()){
//            throw new EmptyDataException();
//        }
//        try {
//            return gson.fromJson(jsonString, StudioRegistrationRequestDto.class);
//        }catch (JsonSyntaxException e){
//            throw new IncorrectDataInputException("Incorrect JSON object");
//        }
//
//    }
}
