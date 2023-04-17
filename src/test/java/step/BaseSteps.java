package step;

import base.BaseTest;
import com.thoughtworks.gauge.Step;
import model.ElementInfo;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BaseSteps extends BaseTest {

    public static int DEFAULT_MAX_ITERATION_COUNT = 1;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 1;

    public BaseSteps() throws IOException {
        String currentWorkingDir = System.getProperty("user.dir");
        initMap(getFileList(currentWorkingDir + "/src"));
        //initMap(getFileList());
    }

    WebElement findElement(String key) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    List<WebElement> findElements(String key) {
        return driver.findElements(getElementInfoToBy(findElementInfoByKey(key)));
    }

    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("name"))) {
            by = By.name(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("linkText")) {
            by = By.linkText(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("partialLinkText"))) {
            by = By.partialLinkText(elementInfo.getValue());
        }
        return by;
    }

    private void clickElement(WebElement element) {
        element.click();
    }

    public WebElement findElementWithKey(String key) {
        return findElement(key);
    }

    //Javascript driverın başlatılması
    private JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    //Javascript scriptlerinin çalışması için gerekli fonksiyon
    private Object executeJS(String script, boolean wait) {
        return wait ? getJSExecutor().executeScript(script, "") : getJSExecutor().executeAsyncScript(script, "");
    }

    private void scrollTo(int x, int y) {
        String script = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(script, true);
    }

    private void hoverElement(WebElement element) {
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(element).perform();
    }

    public void javascriptclicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }


    @Step({"<url> adresine git"})
    public void goToUrl(String url) {
        driver.get(url);
        logger.info(url + " adresine gidiliyor.");
    }

    @Step({"<int> saniye bekle"})
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"<long> milisaniye bekle"})
    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info(milliseconds + " milisaniye bekleniyor.");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step({"Element <key> var mı kontrol et yoksa hata mesajı ver <message>"})
    public void getElementWithKeyIfExistsWithMessage(String key, String message) {
        ElementInfo elementInfo = findElementInfoByKey(key);
        By by = getElementInfoToBy(elementInfo);

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(by).size() > 0) {
                logger.info(key + " elementi bulundu.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assertions.fail(message);
    }

    @Step({"Elementine tıkla <key>"})
    public void clickElement(String key) {
        if (!key.isEmpty()) {
            //hoverElement(findElement(key));
            clickElement(findElement(key));
            logger.info(key + " elementine tıklandı.");
        }
    }


    @Step({"<text> textini <key> elementine yaz"})
    public void ssendKeys(String text, String key) {
        if (!key.equals("")) {
            findElement(key).sendKeys(text);
            logger.info(key + " elementine " + text + " texti yazıldı.");
        }
    }

    @Step({"Şu anki URL <url> değerini içeriyor mu kontrol et"})
    public void checkURLContainsRepeat(String expectedURL) {
        int loopCount = 0;
        String actualURL = "";
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualURL = driver.getCurrentUrl();

            if (actualURL != null && actualURL.contains(expectedURL)) {
                logger.info("Şuanki URL" + expectedURL + " değerini içeriyor.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assertions.fail(
                "Actual URL doesn't match the expected." + "Expected: " + expectedURL + ", Actual: "
                        + actualURL);
    }

    @Step({"<key> elementi <text> değerini içeriyor mu kontrol et"})
    public void checkElementContainsText(String key, String expectedText) {
        Boolean containsText = findElement(key).getText().contains(expectedText);
        logger.info("Beklenen text: " + expectedText);
        logger.info("Gerçek text: " + containsText);
        assertTrue(containsText, "Expected text is not contained!!  " +
                "  Beklenen text ile gerçek text aynı değil!!");
        logger.info(key + " elementi " + "'" + expectedText + "'" + " degerini iceriyor.");
    }

    @Step("<key> elementine <text> yazilir ve enter gonderilir")
    public void sendAndEnter(String key,String text){
        findElement(key).sendKeys(text);
        findElement(key).sendKeys(Keys.ENTER);
    }
    @Step("<key> gidis tarihi <gidisTarihi> secilir")
    public void gidisTarihhh (String key,String gidisTarihi){
       findElement(key).click();
        WebElement elemeent2 = findElement("ileritusu");
        for (int i=0; i<12; i++) {

            WebElement element = driver.findElement(By.cssSelector("[aria-label='" + gidisTarihi + "']"));

            if (element.isDisplayed()) {
                element.click();
                break;

            } else {
                elemeent2.click();

            }
        }
    }
    @Step("<key> donus tarihi <donusTarihi> secilir")
    public void donusTarihiii (String key,String donusTarihi){
        WebElement elemeent = findElement("ileritusu");
          for (int i=0; i<12; i++){
              WebElement element2 = driver.findElement(By.cssSelector("[aria-label='" + donusTarihi + "']"));
              if (element2.isDisplayed()){
                  element2.click();
                  logger.info("ileri tusa basıldı");
                  break;
              }
              else
              {
                  elemeent.click();
              }
          }

        }
    @Step("<kisiSayisi> tane <yolcuSinifi> sinifi secilir")
    public void yolcuDetayi(Integer kisiSayisi, String yolcuSinifi){


        if (yolcuSinifi.equals("Yetiskin")){
            for (int i=1; i<kisiSayisi; i++){
                driver.findElement(By.cssSelector("[data-testid='passengerCountIncrease-0']")).click();
                logger.info("Yetişkin yolcu eklendi");
                logger.info("----------------------------------------------");
            }
        }

        else if (yolcuSinifi.equals("Çocuk")){
            for (int i=0; i<kisiSayisi; i++){
                driver.findElement(By.cssSelector("[data-testid='passengerCountIncrease-1']")).click();
                logger.info("Çocuk yolcu eklendi");
                logger.info("----------------------------------------------");

            }
        }
        else if (yolcuSinifi.equals("Bebek")){
            for (int i=0; i<kisiSayisi; i++){
                driver.findElement(By.cssSelector("[data-testid='passengerCountIncrease-2']")).click();
                logger.info("Bebek yolcu eklendi");
                logger.info("----------------------------------------------");

            }
        }
        else if (yolcuSinifi.equals("65 yaş üstü")){
            for (int i=0; i<kisiSayisi; i++){
                driver.findElement(By.cssSelector("[data-testid='passengerCountIncrease-3']")).click();
                logger.info("65 yaş üstü yolcu eklendi");
                logger.info("----------------------------------------------");

            }
        }
        else if (yolcuSinifi.equals("Öğrenci")){
            for (int i=0; i<kisiSayisi; i++){
                driver.findElement(By.cssSelector("[data-testid='passengerCountIncrease-4']")).click();
            }
        }
        else {
            logger.info("Hatali Bilgi Girildi");
            logger.info("----------------------------------------------");
        }

    }
    @Step("Ucak sinifi <item> olarak secilir")
    public void ucakSinifi(String item) {
        driver.findElement(By.xpath("//div[@class='cabinClassButton'] //*[text()='"+item+"']")).click();
        logger.info("Ucak sinifi: "+item+" olarak secildi");
        logger.info("------------------------------------------------------");

    }

}