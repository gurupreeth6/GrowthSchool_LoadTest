package Growthschool_UserPaths.Scenarios;

import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.text.DecimalFormat;


// required for Gatling HTTP DSL
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class NewOutskillAPI {

    // Method to generate random characters as a name
    private static String generateRandomName(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        StringBuilder name = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            name.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return name.toString();
    }
    
    private static final List<String> OCCUPATIONS = Arrays.asList("Student", "Salaried+Professional", "Founder", "Self-Employed");

    private static final List<String> TIMEZONES = Arrays.asList(
        "Africa/Abidjan", "Africa/Accra", "Africa/Addis_Ababa", "Africa/Algiers", "Africa/Asmara",
        "Africa/Asmera", "Africa/Bamako", "Africa/Bangui", "Africa/Banjul", "Africa/Bissau", 
        "Africa/Blantyre", "Africa/Brazzaville", "Africa/Bujumbura", "Africa/Cairo", "Africa/Casablanca", 
        "Africa/Ceuta", "Africa/Conakry", "Africa/Dakar", "Africa/Dar_es_Salaam", "Africa/Djibouti", 
        "Africa/Douala", "Africa/El_Aaiun", "Africa/Freetown", "Africa/Gaborone", "Africa/Harare", 
        "Africa/Johannesburg", "Africa/Juba", "Africa/Kampala", "Africa/Khartoum", "Africa/Kigali", 
        "Africa/Kinshasa", "Africa/Lagos", "Africa/Libreville", "Africa/Lome", "Africa/Luanda", 
        "Africa/Lubumbashi", "Africa/Lusaka", "Africa/Malabo", "Africa/Maputo", "Africa/Maseru", 
        "Africa/Mbabane", "Africa/Mogadishu", "Africa/Monrovia", "Africa/Nairobi", "Africa/Ndjamena", 
        "Africa/Niamey", "Africa/Nouakchott", "Africa/Ouagadougou", "Africa/Porto-Novo", "Africa/Sao_Tome", 
        "Africa/Timbuktu", "Africa/Tripoli", "Africa/Tunis", "Africa/Windhoek", "America/Adak", 
        "America/Anchorage", "America/Anguilla", "America/Antigua", "America/Araguaina", 
        "America/Argentina/Buenos_Aires", "America/Argentina/Catamarca", "America/Argentina/ComodRivadavia", 
        "America/Argentina/Cordoba", "America/Argentina/Jujuy", "America/Argentina/La_Rioja", 
        "America/Argentina/Mendoza", "America/Argentina/Rio_Gallegos", "America/Argentina/Salta", 
        "America/Argentina/San_Juan", "America/Argentina/San_Luis", "America/Argentina/Tucuman", 
        "America/Argentina/Ushuaia", "America/Aruba", "America/Asuncion", "America/Atikokan", "America/Atka", 
        "America/Bahia", "America/Bahia_Banderas", "America/Barbados", "America/Belem", "America/Belize", 
        "America/Blanc-Sablon", "America/Boa_Vista", "America/Bogota", "America/Boise", 
        "US/Central", "US/Eastern", "US/Mountain", "US/Pacific", "US/Alaska"
    );

    private static final List<String> COUNTRY_CODES = Arrays.asList(
        "af", "ax", "al", "dz", "as", "ad", "ao", "ai", "aq", "ag", "ar", "am", "aw", "au", "at", "az",
        "bs", "bh", "bd", "bb", "by", "be", "bz", "bj", "bm", "bt", "bo", "bq", "ba", "bw", "bv", "br",
        "io", "bn", "bg", "bf", "bi", "kh", "cm", "ca", "cv", "ky", "cf", "td", "cl", "cn", "cx", "cc", 
        "co", "km", "cg", "cd", "ck", "cr", "ci", "hr", "cu", "cw", "cy", "cz", "dk", "dj", "dm", "do", 
        "ua", "ae", "gb", "us", "um", "uy", "uz", "vu", "ve", "vn", "vg", "vi", "wf", "eh", "ye", "zm", 
        "zw", "default"
    );

    public static Iterator<Map<String, Object>> userFeeder() {
        return Stream.generate((Supplier<Map<String, Object>>) () -> {
            Random rand = new Random();
            String uuid = UUID.randomUUID().toString().replace("-", "");

            // Generate random phone number
            int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
            int num2 = rand.nextInt(743);
            int num3 = rand.nextInt(10000);

            DecimalFormat df3 = new DecimalFormat("000"); // 3 digits format
            DecimalFormat df4 = new DecimalFormat("0000"); // 4 digits format

            String phoneNumber = df3.format(num1) + df3.format(num2) + df4.format(num3);

            // Randomly select an occupation from the list
            String occupation = OCCUPATIONS.get(rand.nextInt(OCCUPATIONS.size()));

            // Randomly select a timezone from the list
            String timezone = TIMEZONES.get(rand.nextInt(TIMEZONES.size()));

            String countryCode = COUNTRY_CODES.get(rand.nextInt(COUNTRY_CODES.size()));

            // Generate a random name of length 8
            String name = generateRandomName(8);

            // Create a map to hold the user's information
            HashMap<String, Object> hmap = new HashMap<>();
            hmap.put("uuid", uuid); // UUID fieldf
            hmap.put("name", name); // Name field
            hmap.put("phoneNumber", phoneNumber); // Phone number field
            hmap.put("occupation", occupation); // Occupation field
            hmap.put("timezone", timezone); // Timezone field
            hmap.put("countryCode", countryCode); // Country code field

            return hmap;
        }).iterator();
    }
    //Broadcasting Workshop
    public static ChainBuilder GetFormResponse =
        exec(http("Get form response")
                .get("/leads/form/1671/responses")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImdzLWZvcm1zLWFwaSIsImFwaSI6ImxlYWRzIiwiY3JlYXRlZEJ5IjoibGF2amliYWRzaGFoIn0.kyBAVTMRLrSesvfDhZm44QaQLXZfq5YlBUKLMtCYfEg")
                .header("gs-forms-user-auth-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoic3RpIHRlc3RpbmciLCJlbWFpbCI6InNyaXRlc3RpbmcxMjNAZ21haWwuY29tIiwicGhvbmUiOiIrOTEtOTQ5MTg2NDA5NCIsImZvcm1zTGVhZElkIjoxMDA1NjkzLCJpc051bWJlclZlcmlmaWVkIjpmYWxzZSwiaWF0IjoxNzI1OTg0Mjc1fQ.b56EVqqJ17VsEvyTWlG5HJKAj7IejKStanJWaCLwdqk"));

    public static ChainBuilder GetFormsQuestionFlow =
        exec(http("Get form question flow")
                .get("/forms/1671/questionflow")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImdzLWZvcm1zLWFwaSIsImFwaSI6ImxlYWRzIiwiY3JlYXRlZEJ5IjoibGF2amliYWRzaGFoIn0.kyBAVTMRLrSesvfDhZm44QaQLXZfq5YlBUKLMtCYfEg"));

    public static ChainBuilder GetLeadDetails =
        feed(userFeeder())
            .exec(http("Get lead details")
                .get("/leads/leaddetails?leadUUID=${uuid}")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImdzLWZvcm1zLWFwaSIsImFwaSI6ImxlYWRzIiwiY3JlYXRlZEJ5IjoibGF2amliYWRzaGFoIn0.kyBAVTMRLrSesvfDhZm44QaQLXZfq5YlBUKLMtCYfEg"));

    public static ChainBuilder GetWebinarForms =
        exec(http("Get webinar forms")
                .get("/forms/webinar-form?form_id=1671")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImdzLWZvcm1zLWFwaSIsImFwaSI6ImxlYWRzIiwiY3JlYXRlZEJ5IjoibGF2amliYWRzaGFoIn0.kyBAVTMRLrSesvfDhZm44QaQLXZfq5YlBUKLMtCYfEg"));

    public static ChainBuilder GetWebinarSchedules =
        exec(http("Get webinar schedules")
                .get("https://outskill-api.growthschool.io/v1/webinarSchedules/prefered-webinarschedule?formId=1671&country=${countryCode}&occupation=${occupation}&timezone=${timezone}")
                .check(jsonPath("$.webinarScheduleDetails.id").saveAs("scheduleId")));

    public static ChainBuilder WebinarSignups =
        exec(http("Webinar Signups")
                .post("https://outskill-api.growthschool.io/v1/webinarSignups")
                .body(StringBody("{\"name\":\"${name}\",\"email\":\"${name}@mailinator.com\",\"phone\":\"91${phoneNumber}\",\"webinarId\":2314,\"webinarScheduleId\":${scheduleId},\"timezone\":\"${timezone}\"}")));
}
