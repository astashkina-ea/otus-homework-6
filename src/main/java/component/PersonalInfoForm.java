package component;

import data.MessengersData;
import data.PersonalInfoData;
import data.countries.CountryData;
import data.countries.cities.ICity;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonalInfoForm extends AbsBaseComponent {

    public PersonalInfoForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='date_of_birth']")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//input[contains(@name,'country')]")
    private WebElement сountryFieldWait;

    @FindBy(xpath = "//input[@name='country']/parent::label/parent::div")
    private WebElement сountryField;

    @FindBy(xpath = "//input[contains(@data-title,'Город') and not(@disabled='disabled')]")
    private WebElement cityFieldWait;

    @FindBy(xpath = "//input[@data-title='Город']/parent::label/parent::div")
    private WebElement cityField;

    @FindBy(xpath = "//input[@data-title='Уровень знания английского языка']/parent::label/parent::div")
    private WebElement languageField;

    @FindBy(xpath = "//input[@id='id_ready_to_relocate_0']//following-sibling::span")
    private WebElement relocateRadioButton;

    @FindBy(css = "input[id='id_ready_to_relocate_0']")
    private WebElement relocateRadioButtonChecked;

    //Формат работы
    @FindBy(xpath = "//input[@title='Полный день']/parent::label/span")
    private WebElement workFormatCheckBox;

    @FindBy(css = "input[title='Полный день']")
    private WebElement workFormatCheckBoxChecked;

    @FindBy(xpath = "//input[@name='contact-0-service']/following-sibling::div")
    private WebElement сommunicationFieldOne;

    @FindBy(css = "input[id='id_contact-0-value']")
    private WebElement сontactOneField;

    @FindBy(xpath = "//input[@name='contact-1-service']/following-sibling::div")
    private WebElement сommunicationFieldTwo;

    @FindBy(css = "input[id='id_contact-1-value']")
    private WebElement contactTwoField;

    @FindBy(css = "select[id='id_gender']")
    private WebElement gendersField;

    @FindBy(css = "option[value='f']")
    private WebElement genderSelection;

    @FindBy(css = "input[id='id_company']")
    private WebElement companyField;

    @FindBy(css = "input[id='id_work']")
    private WebElement positionField;

    @FindBy(css = "select[id ='id_experience-0-experience']")
    private WebElement experienceField;

    @FindBy(xpath = "//option[text()='Java']")
    private WebElement experienceSelection;

    @FindBy(css = "select[id ='id_experience-0-level']")
    private WebElement experienceLevelField;

    @FindBy(xpath = "//option[text()='Более 10 лет']")
    private WebElement experienceLevelSelection;

    @FindBy(css = "button[name='continue']")
    private WebElement saveButton;

    private String userInputFioSelectorTemplate = "#id_%s";
    private String locationSelectorTemplate = "button[title='%s']";
    private String messengerLocatorTemplate = "button[@data-value='%s']";
    private DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String languageSelectorTemplate = "button[title='%s']";

    public PersonalInfoForm inputInfo(PersonalInfoData personalInfoData, String data) {
        String selector = String.format(String.format(userInputFioSelectorTemplate, personalInfoData.getName()));
        enterToTextArea($(By.cssSelector(selector)), data);
        return this;
    }

    public PersonalInfoForm inputDateOfBirthInField(LocalDate birthdate) {
        enterToTextArea(dateOfBirthField, birthdate.format(date));
        return this;
    }

    public PersonalInfoForm selectCountry(CountryData countryData) {
        String locator = String.format(locationSelectorTemplate, countryData.getName());
        webDriverWait.waitForVisibleElement(сountryField);
        сountryField.click();
        webDriverWait.waitForVisibleElement($(By.cssSelector(locator)));
        $(By.cssSelector(locator)).click();
        return this;
    }

    public PersonalInfoForm selectCity(ICity cityData) {
        String locator = String.format(locationSelectorTemplate, cityData.getName());
        webDriverWait.waitForAttributeNotContains(сountryFieldWait, "disabled", "disabled");
        webDriverWait.waitForAttributeNotContains(cityFieldWait, "disabled", "disabled");
        cityField.click();
        webDriverWait.waitForVisibleElement($(By.cssSelector(locator)));
        $(By.cssSelector(locator)).click();
        return this;
    }

    public PersonalInfoForm selectLevelLanguage(String levelLanguageValue) {
        String selector = String.format(languageSelectorTemplate, levelLanguageValue);
        languageField.click();
        $(By.cssSelector(selector)).click();
        return this;
    }

    public PersonalInfoForm setRelocate() {
        relocateRadioButton.click();
        return this;
    }

    public PersonalInfoForm setWorkFormat() {
        workFormatCheckBox.click();
        return this;
    }

    public PersonalInfoForm inputContact1(MessengersData messengersData, String сontactOneValue) {
        String selector = String.format("//" + messengerLocatorTemplate, messengersData.getName());
        moveToElementAndClick(сommunicationFieldOne);
        webDriverWait.waitForVisibleElement($(By.xpath(selector)));
        $(By.xpath(selector)).click();
        сontactOneField.click();
        enterToTextArea(сontactOneField, сontactOneValue);
        return this;
    }

    public PersonalInfoForm inputContact2(MessengersData messengersData, String сontactOneValue) {
        String selector = String.format("//input[@name='contact-1-service']/parent::label/following-sibling::div/descendant::" + messengerLocatorTemplate, messengersData.getName());
        moveToElementAndClick(сommunicationFieldTwo);
        webDriverWait.waitForVisibleElement($(By.xpath(selector)));
        $(By.xpath(selector)).click();
        contactTwoField.click();
        enterToTextArea(contactTwoField, сontactOneValue);
        return this;
    }

    public PersonalInfoForm selectGender() {
        gendersField.click();
        genderSelection.click();
        return this;
    }

    public PersonalInfoForm inputCompany(String companyValue) {
        enterToTextArea(companyField, companyValue);
        return this;
    }

    public PersonalInfoForm inputPosition(String positionValue) {
        enterToTextArea(positionField, positionValue);
        return this;
    }

    public PersonalInfoForm selectExperience() {
        experienceField.click();
        experienceSelection.click();
        return this;
    }

    public PersonalInfoForm selectExperienceLevel() {
        experienceLevelField.click();
        experienceLevelSelection.click();
        return this;
    }

    public PersonalInfoForm clickSave() {
        saveButton.click();
        return this;
    }

    public PersonalInfoForm personalInfoShouldBeSameAs(PersonalInfoData personalInfoData, String expectedDate) {
        String selector = String.format(String.format(userInputFioSelectorTemplate, personalInfoData.getName()));
        Assertions.assertEquals(expectedDate, $(By.cssSelector(selector)).getAttribute("value"));
        return this;
    }

    public PersonalInfoForm dateOfBirthShouldBeSameAs(LocalDate expectedDate) {
        Assertions.assertEquals(expectedDate.format(date), dateOfBirthField.getAttribute("value"));
        return this;
    }

    public PersonalInfoForm countryShouldBeSameAs(String expectedDate) {
        Assertions.assertEquals(expectedDate, сountryField.getText());
        return this;
    }

    public PersonalInfoForm cityShouldBeSameAs(String expectedDate) {
        Assertions.assertEquals(expectedDate, cityField.getText());
        return this;
    }

    public PersonalInfoForm levelLanguageCityShouldBeSameAs(String expectedDate) {
        Assertions.assertEquals(expectedDate, languageField.getText());
        return this;
    }

    public PersonalInfoForm relocateShouldBeSelected() {
        Assertions.assertTrue(relocateRadioButtonChecked.isSelected());
        return this;
    }

    public PersonalInfoForm workFormatShouldBeSelected() {
        Assertions.assertTrue(workFormatCheckBoxChecked.isSelected());
        return this;
    }

    public PersonalInfoForm contact1ShouldBeSameAs(String expectedDate) {
        Assertions.assertEquals(expectedDate, сontactOneField.getAttribute("value"));
        return this;
    }

    public PersonalInfoForm contact2ShouldBeSameAs(String expectedDate) {
        Assertions.assertEquals(expectedDate, contactTwoField.getAttribute("value"));
        return this;
    }

    public PersonalInfoForm genderShouldBeSelected() {
        Assertions.assertTrue(genderSelection.isSelected());
        return this;
    }

    public PersonalInfoForm companyShouldBeSameAs(String expectedDate) {
        Assertions.assertEquals(expectedDate, companyField.getAttribute("value"));
        return this;
    }

    public PersonalInfoForm positionShouldBeSameAs(String expectedDate) {
        Assertions.assertEquals(expectedDate, positionField.getAttribute("value"));
        return this;
    }

    public PersonalInfoForm experienceShouldBeSelected() {
        Assertions.assertTrue(experienceSelection.isSelected());
        return this;
    }

    public PersonalInfoForm experienceLevelShouldBeSelected() {
        Assertions.assertTrue(experienceLevelSelection.isSelected());
        return this;
    }
}