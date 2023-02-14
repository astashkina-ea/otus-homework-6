import component.DropDownMenuComponent;
import component.HeaderComponentAuthArea;
import component.HeaderComponentUnauthArea;
import component.PersonalInfoForm;
import component.modal.LoginModal;
import data.MessengersData;
import data.PersonalInfoData;
import data.countries.CountryData;
import data.countries.cities.RussiaCitiesData;
import exception.BrowserNorSupportedException;
import factories.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainBasePage;

import java.time.LocalDate;

public class MainTest {

    private WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    @BeforeAll
    public static void setUpDriver() throws BrowserNorSupportedException {
        new WebDriverFactory().setUp();
    }

    @BeforeEach
    public void init() throws BrowserNorSupportedException {
        driver = new WebDriverFactory().createDriver();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
    @Test
    public void otusTest() {

        MainBasePage mainPage = new MainBasePage(driver);
        HeaderComponentUnauthArea headerComponent = new HeaderComponentUnauthArea(driver);
        LoginModal loginModal = new LoginModal(driver);
        HeaderComponentAuthArea headerComponentAuthArea = new HeaderComponentAuthArea(driver);
        DropDownMenuComponent dropDownMenuComponent = new DropDownMenuComponent(driver);
        PersonalInfoForm personalInfoForm = new PersonalInfoForm(driver);

        String fname = "Тест";
        String lname = "Тестов";
        String fname_latin = "Test";
        String lname_latin = "Testov";
        String blog_name = "Тест";
        LocalDate dateOfBirth = LocalDate.now().minusYears(30);
        String levelLanguage = "Выше среднего (Upper Intermediate)";
        String contact1 = "@testtt";
        String contact2 = "@testttt";
        String company = "OOO Тест";
        String position = "Главный тестировщик";

        mainPage.open();
        logger.info("Открыли https://otus.ru");
        loginModal.modalShouldNotBeVisible();

        headerComponent.clickSignInButton();
        loginModal.modalShouldBeVisible();
        loginModal.fillAuthForm();
        logger.info("Авторизовались на сайте");

        headerComponentAuthArea.moveToUserItem();
        dropDownMenuComponent.clickUserItem();
        logger.info("Вошли в личный кабинет");

        personalInfoForm
                .inputInfo(PersonalInfoData.FNAME, fname)
                .inputInfo(PersonalInfoData.LNAME, lname)
                .inputInfo(PersonalInfoData.FNAME_LATIN, fname_latin)
                .inputInfo(PersonalInfoData.LNAME_LATIN, lname_latin)
                .inputInfo(PersonalInfoData.BLOG_NAME, blog_name)
                .inputDateOfBirthInField(dateOfBirth)
                .selectCountry(CountryData.RUSSIA)
                .selectCity(RussiaCitiesData.PENZA)
                .selectLevelLanguage(levelLanguage)
                .setRelocate()
                .setWorkFormat()
                .inputContact1(MessengersData.SKYPE, contact1)
                .inputContact2(MessengersData.TELEGRAM, contact2)
                .selectGender()
                .inputCompany(company)
                .inputPosition(position)
                .selectExperience()
                .selectExperienceLevel()
                .clickSave();
        logger.info("В разделе \"О себе\" заполнили все поля \"Личные данные\" и добавили не менее двух контактов и нажали сохранить");

        driver.manage().deleteAllCookies();
        mainPage.open();
        logger.info("Открыли https://otus.ru в \"чистом браузере\"");

        headerComponent.clickSignInButton();
        loginModal.fillAuthForm();
        logger.info("Авторизовались на сайте");

        headerComponentAuthArea.moveToUserItem();
        dropDownMenuComponent.clickUserItem();
        logger.info("Вошли в личный кабинет");

        personalInfoForm
                .personalInfoShouldBeSameAs(PersonalInfoData.FNAME, fname)
                .personalInfoShouldBeSameAs(PersonalInfoData.LNAME, lname)
                .personalInfoShouldBeSameAs(PersonalInfoData.FNAME_LATIN, fname_latin)
                .personalInfoShouldBeSameAs(PersonalInfoData.LNAME_LATIN, lname_latin)
                .personalInfoShouldBeSameAs(PersonalInfoData.BLOG_NAME, blog_name)
                .dateOfBirthShouldBeSameAs(LocalDate.now().minusYears(30))
                .countryShouldBeSameAs(CountryData.RUSSIA.getName())
                .cityShouldBeSameAs(RussiaCitiesData.PENZA.getName())
                .levelLanguageCityShouldBeSameAs(levelLanguage)
                .relocateShouldBeSelected()
                .workFormatShouldBeSelected()
                .contact1ShouldBeSameAs(contact1)
                .contact2ShouldBeSameAs(contact2)
                .genderShouldBeSelected()
                .companyShouldBeSameAs(company)
                .positionShouldBeSameAs(position)
                .experienceShouldBeSelected()
                .experienceLevelShouldBeSelected();
        logger.info("Проверили, что в разделе \"О себе\" отображаются указанные ранее данные");
    }
}