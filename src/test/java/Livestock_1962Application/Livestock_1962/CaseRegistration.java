package Livestock_1962Application.Livestock_1962;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaseRegistration {

	public static void main(String[] args) throws Exception {
		
		//System.setProperty("webdriver.chromedriver",
			//	"C:\\Users\\Hariprasad R\\eclipse-workspace\\Livestock_1962\\src\\test\\resources\\Drivers\\Chromedriver\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Maximize window and open URL
		driver.manage().window().maximize();
		driver.get("https://livestocktmqa.dhanushinfotech.com/");

		// Log in
		WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		usernameField.sendKeys("sandycallcentre@dhspltd.com");
		driver.findElement(By.id("password")).sendKeys("vmedproqa");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='captchaText']")).sendKeys("gfvc");

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Login')]")));
		loginButton.click();
		Thread.sleep(2000);

		// Handle the alert pop-up
		driver.findElement(By.xpath("//button[@id='bypassLgn']")).click();
		Thread.sleep(3000);

		// Farmer Creation
		WebElement FarmerListAdd = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-blue btn-icon float-right']")));
		FarmerListAdd.click();

		WebElement CallerNo = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col-xl-4 col-md-3']//div[@class='form-group']//input[@type='text']")));
		CallerNo.sendKeys("8800112252");
		Thread.sleep(2000);

		WebElement CallId = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[3]")));
		CallId.sendKeys("YT865722578");

		WebElement callerType = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='caller_type_id']")));
		Select Callertype1 = new Select(callerType);
		Callertype1.selectByVisibleText("Staff-VLDA");

		WebElement callType = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='call_type_id']")));
		Select Calltype1 = new Select(callType);
		Calltype1.selectByVisibleText("Emergency Case(Case regn)");

		WebElement RequestFor = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='request_id']")));
		Select RequestFor1 = new Select(RequestFor);
		RequestFor1.selectByVisibleText("Veterinary Services");

		driver.findElement(By.xpath("//input[@formcontrolname='first_name']")).sendKeys("Srinivas");
		driver.findElement(By.xpath("//input[@formcontrolname='last_name']")).sendKeys("R");

		WebElement DistrictName = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='district_id']")));
		Select DistrictName1 = new Select(DistrictName);
		DistrictName1.selectByVisibleText("Ambala");
		Thread.sleep(2000);

		WebElement Mandal = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mandal_id")));
		Select Mandal1 = new Select(Mandal);
		Mandal1.selectByVisibleText("Ambala");
		Thread.sleep(2000);

		WebElement Village = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("village_id")));
		Select Village1 = new Select(Village);
		Village1.selectByVisibleText("Adho Majra (278)");

		WebElement Gender = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("branch_name")));
		Select Gender1 = new Select(Gender);
		Gender1.selectByVisibleText("Male");

		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@formcontrolname='latitude']")).sendKeys("17.87656");
		driver.findElement(By.xpath("//input[@formcontrolname='longitude']")).sendKeys("78.99765");
		driver.findElement(By.xpath("//textarea[@formcontrolname='address']")).sendKeys("Ambala");
		driver.findElement(By.xpath("//textarea[@formcontrolname='remarks']")).sendKeys("Test");
		WebElement SaveFarmer = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")));
		SaveFarmer.click();
		Thread.sleep(2000);

		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);

		// Case Creation
		// Animal One
		WebElement tagNo = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='luid']")));
		tagNo.sendKeys("TAG11248-Cow");

		// Select Animal Name (Cow)
		WebElement animalName = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='animal_type_id']")));
		Select animalNameSelect = new Select(animalName);
		animalNameSelect.selectByVisibleText("Cow");
		Thread.sleep(2000);
		// Select Breed Type (Brown Swiss)
		WebElement breedType = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='breed_type_id']")));
		Select breedTypeSelect = new Select(breedType);
		breedTypeSelect.selectByVisibleText("Brown Swiss");
		Thread.sleep(2000);
		// Select Gender (Female, value 2)
		WebElement genderAnimal = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='col-xl-3 col-md-3 mb-2']//select[@formcontrolname='gender_id']")));
		Select genderSelect = new Select(genderAnimal);
		genderSelect.selectByValue("2");
		Thread.sleep(2000);
		// Select Service Type (Accidental Cases)
		WebElement serviceType = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='service_type_id']")));
		Select serviceTypeSelect = new Select(serviceType);
		serviceTypeSelect.selectByVisibleText("Accidental Cases");

		// Enter age and complaint
		driver.findElement(By.xpath("//input[@formcontrolname='age']")).sendKeys("15");
		driver.findElement(By.xpath("//textarea[@formcontrolname='complaint']")).sendKeys("Fever");

		// Click 'Add' button
		WebElement animalAdd = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add')]")));
		animalAdd.click();
		Thread.sleep(2000);

		// Animal Two
		WebElement tagNo2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='luid']")));
		tagNo2.sendKeys("TAG11248-Goat");

		// Select Animal Name (Cow)
		WebElement animalName2 = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='animal_type_id']")));
		Select animalNameSelect2 = new Select(animalName2);
		animalNameSelect2.selectByVisibleText("Goat");
		Thread.sleep(2000);
		// Select Breed Type (Brown Swiss)
		WebElement breedType2 = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='breed_type_id']")));
		Select breedTypeSelect2 = new Select(breedType2);
		breedTypeSelect2.selectByVisibleText("Black Bengal/Beri Beri");
		Thread.sleep(2000);
		// Select Gender (Female, value 2)
		WebElement genderAnimal2 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='col-xl-3 col-md-3 mb-2']//select[@formcontrolname='gender_id']")));
		Select genderSelect2 = new Select(genderAnimal2);
		genderSelect2.selectByValue("1");
		Thread.sleep(2000);
		// Select Service Type (Accidental Cases)
		WebElement serviceType2 = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='service_type_id']")));
		Select serviceTypeSelect2 = new Select(serviceType2);
		serviceTypeSelect2.selectByVisibleText("Emergency Clinical Cases");

		// Enter age and complaint
		driver.findElement(By.xpath("//input[@formcontrolname='age']")).sendKeys("13");
		driver.findElement(By.xpath("//textarea[@formcontrolname='complaint']")).sendKeys("dengue");

		// Click 'Add' button
		WebElement animalAdd2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add')]")));
		animalAdd2.click();
		Thread.sleep(2000);

		// Wait for the Save button and click it
		WebElement animalSave = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='float-right mt-2 mb-2 ng-star-inserted']//button[contains(text(),'Save')]")));
		animalSave.click();

		// Handle the alert pop-up
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();

		// Scroll down to reveal the Vehicle Number dropdown
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0, 500)");

		// Select Vehicle Number
		WebElement vehicleNumber = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='col-xl-4 col-md-4']//div//select[@id='branch_name']")));
		Select vehicleNumberSelect = new Select(vehicleNumber);
		vehicleNumberSelect.selectByVisibleText("TS09GB2117-Balapur city");

		// Scroll down further if needed
		jsExecutor.executeScript("window.scrollBy(0, 500)");

		// Enter time and click 'Submit'
		WebElement timeInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='time']")));
		timeInput.sendKeys("15:03");

		WebElement submitButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Submit']")));
		submitButton.click();
	}
}