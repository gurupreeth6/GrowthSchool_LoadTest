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


public class OutskillAPI {

    //Broadcasting Workshop
    public static ChainBuilder BroadcastingWorkshop =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Broadcasting Workshop API")
                .get("/v1/webinars/live?page=1"));


    //Workshop of the day
    public static ChainBuilder WorkshopOfTheDay =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Workshop of the day API")
                .get("/v1/featured-workshops/1"));


    //Workshop Topics and Mentor
    public static ChainBuilder TopicsAndMentor =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Topic And Mentor API")
                .get("/v1/webinars/topics-and-mentors"));

    //Upcoming Workshop 1
    public static ChainBuilder UpcomingWorkshop =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Upcoming Workshop API")
                .get("/v2/webinars/upcoming?page=1"));

    //Upcoming Workshop 2
    public static ChainBuilder UpcomingWorkshop2 =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Upcoming Workshop API 2")
                .get("/v1/webinars/upcoming"));

    //Get Recent Feedback
    public static ChainBuilder GetRecentFeedback =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Get Recent Feedback API")
                .get("/v1/webinarFeedbacks/check-recent-feedback"));


     //Submit Feedback
    public static ChainBuilder SubmitFeedback =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Submit Feedback API")
                .post("/v1/webinarFeedbacks")
                .header("content-type", "application/json")
                .body(StringBody("{\"satisfactionScore\": \"1\",\"suggestionInputByUser\": \"Great Content\",\"suggestionsSelected\": [\"Technical Issue\"],\"type\": \"WHILE_LEAVING\",\"webinarSignupId\": \"389622\",\"webinarScheduleId\": \"107283\",\"webinarId\": \"8\"}")));


    //Get Audio Books topic
    public static ChainBuilder GetAudioBooksTopic =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Get Audio books Topic")
                .get("/v1/audio-books/topics"));

    
    //Get Audio Books topic
    public static ChainBuilder GetFeaturedAudioBooks =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Get Featured Audio books")
                .get("/v1/audio-books/featured"));

    //Get All Audio Books
    public static ChainBuilder GetAllAudioBooks =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Get All Audio books")
                .get("/v1/audio-books"));

    //Get Guide topic
    public static ChainBuilder GetGuideTopic =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Get Guide Topic")
                .get("/v1/guides/topics"));

    //Get Featured Guides
    public static ChainBuilder GetFeaturedGuides =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Get Featured Guides")
                .get("/v1/guides/featured"));

    //Get All Guides
    public static ChainBuilder GetAllGuides =
        exec(addCookie(Cookie("connect.sid", "s%3Ax23lch1Tf8IEse9-y3JY-L9iTM3Wiu7h.Se%2BcRy0RiESZd9WesvgKBlGob3pU%2FTnJxHtA2qfXkWk").withDomain("growthschool.io").withPath("/")))
        
                .exec(http("Get All Guides")
                .get("/v1/guides/allguides"));

    // Webinar live signup API
    public static ChainBuilder TestWaitlist =
            exec(http("Test Waitlist")
                .get("/v1/test-waitlist"));
    
}
