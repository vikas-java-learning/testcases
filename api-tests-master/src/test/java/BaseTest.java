import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;
import utils.ApplicationProperties;


public class BaseTest {

    private Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeTest() {
        logger.info("Initializing Suite");
        RestAssured.baseURI = ApplicationProperties.INSTANCE.getBaseUrl();
        RestAssured.port = ApplicationProperties.INSTANCE.getPort();
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Terminating Suite");
    }


}
