package Growthschool_UserPaths.Scenarios;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.Session;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.text.DecimalFormat;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class UserLoginAPI {

    private static Iterator<Map<String, Object>> userFeeder =
            Stream.generate((Supplier<Map<String,Object>>) () -> {
                Random rand = new Random();
                String uuid = UUID.randomUUID().toString().replace("-", "");

                int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
                int num2 = rand.nextInt(743);
                int num3 = rand.nextInt(10000);

                DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
                DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

                String phoneNumber = df3.format(num1) + df3.format(num2) + df4.format(num3);
                String name = uuid.substring(0, Math.min(8, uuid.length()));

                HashMap<String,Object> hmap = new HashMap<String,Object>();
                hmap.put("phoneNumber", phoneNumber);
                hmap.put("name", name);
                return hmap;
            }).iterator();

    // Signup as a user
    public static ChainBuilder UserSignup =
                    feed(userFeeder)
                        .exec(http("User Signup")
                            .post("/v1/webinarSignups")
                            .header("content-type", "application/json")
                            .body(StringBody("{\"name\": \"${name}\",\"email\": \"${name}@mailinator.com\",\"phone\": \"${phoneNumber}\",\"webinarId\": \"8\",\"webinarScheduleId\": \"213730\",\"timezone\": \"Asia/Calcutta\"}"))
                            .check(jsonPath("$.uuid").saveAs("uuId")));

    // Webinar live signup API
    public static ChainBuilder WebinarLiveSignup =
            exec(http("Webinar Live signup")
                .get("https://webinar.growthschool.io/live/213730?signup=${uuId}"));

    // Get details of Signup
    public static ChainBuilder GetSignupDetails =
            exec(http("Get Signup details")
                .get("/v1/webinarSignups?uuid=${uuId}"));

    //Get Webinar Schedules
    public static ChainBuilder GetWebinarScheduleDetails =
            exec(http("Get webinar schedule details")
                .get("/v1/webinarSchedules/213730"));

    //Get Webinar details
    public static ChainBuilder GetWebinarDetails =
            exec(http("Get webinar details")
                .get("/v1/webinars/8")
                .check(jsonPath("$.videoUuid").saveAs("videoId")));

    //Get static manifest API
    public static ChainBuilder GetStaticManifest =
            exec(http("Get manifest API")
                .get("https://webinar.growthschool.io/static/manifest.json"));

    //Get Webinar Host details
    public static ChainBuilder GetWebinarHostsDetails =
            exec(http("Get Webinar Host Details")
                .get("/v1/webinars/8/hosts"));

    //Get Webinar resources details
    public static ChainBuilder GetWebinarResourcesDetails =
            exec(http("Get webinar resources details")
                .get("/v1/webinars/8/resources"));

    //Get Webinar Metadata
    public static ChainBuilder GetWebinarMetadata =
            exec(http("Get Webinar Metadata")
                .get("/v1/webinars/8/webinarMetadata"));
    
    //Get live People graph
    public static ChainBuilder GetLivePeopleGraph =
            exec(http("Live People graph API")
                .get("/v1/livePeopleGraphs?webinarId=8"));
    
    //Generate Firebase token for SDK
    public static ChainBuilder GenerateFirebaseToken =
            exec(http("Generate Firebase token")
                .post("/v1/users/generate-firebase-token")
                .header("content-type", "application/json")
                .body(StringBody("{\"userEmail\":\"${name}@mailinator.com\"}"))
                .check(jsonPath("$.message").ofString().is("successfull")));

    //Get the video stream
    public static ChainBuilder GetVideoStream =
            exec(http("Get video stream")
                .get("https://api.growthschool.io/videos/${videoId}/streams")
                .check(jsonPath("$.gumlet.hls.url").saveAs("gumletUrl"))
                .check(jsonPath("$.thumbnails.png").saveAs("thumbnailUrl")));

    //Get Gumlet url
    public static ChainBuilder GetGumletUrl1 =
            exec(http("Get gumlet url 1")
                .get("${gumletUrl}"));

    //Get Gumlet url2
    public static ChainBuilder GetGumletUrl2 =
            exec(http("Get gumlet url 2")
                .get("${gumletUrl}".split("/main")[0]+"_0_en_192k.m3u8"));

    //Get Gumlet url3
    public static ChainBuilder GetGumletUrl3 =
            exec(http("Get gumlet url 3")
                .get("${gumletUrl}".split("/main")[0]+"_0_1078p.m3u8"));
    
    //Get Gumlet url4
    public static ChainBuilder GetGumletUrl4 =
            exec(http("Get gumlet url 4")
                .get("${gumletUrl}".split("/main")[0]+"_0_en_192k.mp4"));

    //Get Gumlet url3
    public static ChainBuilder GetGumletUrl5 =
            exec(http("Get gumlet url 5")
                .get("${gumletUrl}".split("/main")[0]+"_0_1078p.mp4"));

    //Get the Thumbnail from aws S3
    /*public static ChainBuilder GetThumbnail =
            exec(http("Get thumbnail png")
                .get("${thumbnailUrl}"));*/
    

    // Send Messages
    public static ChainBuilder SendMessages =
                    feed(userFeeder)
                        .exec(http("Send messages")
                            .post("/v1/messages/8")
                            .header("content-type", "application/json")
                            .body(StringBody("{\"time\":\"00:00:09\",\"name\": \"${name}\",\"text\": \"Hello\"}")));

    // Send Messages
    public static ChainBuilder GetAllMessages =
                    feed(userFeeder)
                        .exec(http("Get all messages")
                            .get("/v1/messages/all/8")
                            .header("content-type", "application/json"));

}
