package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterPage extends BasePage{
    @FindBy(id = "gender-male")
    WebElement genderMaleRadioButton;
    @FindBy(id = "gender-female")
    WebElement genderFemaleRadioButton;
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement registerLink;
    @FindBy(id = "FirstName")
    WebElement firstNameField;
    @FindBy(id = "FirstName-error")
    WebElement firstNameError;
    @FindBy(id = "LastName")
    WebElement lastNameField;
    @FindBy(id = "LastName-error")
    WebElement lastNameError;
    @FindBy(name = "DateOfBirthDay")
    WebElement dateOfBirthDaySelect;
    @FindBy(name = "DateOfBirthMonth")
    WebElement dateOfBirthMonthSelect;
    @FindBy(name = "DateOfBirthYear")
    WebElement dateOfBirthYearSelect;
    @FindBy(id = "Email")
    WebElement emailField;
    @FindBy(id = "Email-error")
    WebElement emailError;
    @FindBy(id = "Company")
    WebElement companyNameField;
    @FindBy(id = "Newsletter")
    WebElement newsletterCheckbox;
    @FindBy(id = "Password")
    WebElement passwordField;
    @FindBy(id = "ConfirmPassword")
    WebElement confirmPasswordField;
    @FindBy(id = "ConfirmPassword-error")
    WebElement confirmPasswordError;
    @FindBy(id = "register-button")
    WebElement registerButton;

    public void allRequiredFieldValidation(){
        waitAndClick(registerButton);
        Assert.assertEquals(firstNameError.getText(), "First name is required.");
        Assert.assertEquals(lastNameError.getText(), "Last name is required.");
        Assert.assertEquals(emailError.getText(), "Email is required.");
        Assert.assertEquals(confirmPasswordError.getText(), "Password is required.");
        passwordAndConfirmPasswordValidation();
    }

    public void passwordAndConfirmPasswordValidation(){
        clickAndType(passwordField, "abcdef");
        clickAndType(confirmPasswordField, "123456");
        Assert.assertEquals(confirmPasswordError.getText(), "The password and confirmation password do not match.");
    }

    public void userRegistration(){
        waitAndClick(genderMaleRadioButton);
        clickAndType(firstNameField, utils.GenerateFakeData.fakeFirstName());
        clickAndType(lastNameField, utils.GenerateFakeData.fakeLastName());
        clickAndType(emailField, utils.GenerateFakeData.fakeEmail());
        String password = utils.GenerateFakeData.generatePassword(8,16,true, true, true);
        clickAndType(passwordField, password);
        clickAndType(confirmPasswordField, password);
        waitAndClick(registerButton);
    }
}
