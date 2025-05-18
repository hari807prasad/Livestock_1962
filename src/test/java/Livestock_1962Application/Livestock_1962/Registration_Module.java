package Livestock_1962Application.Livestock_1962;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Registration_Module {

	WebDriver driver;
	WebDriverWait wait;

	@Parameters("Browser")
	@BeforeClass
	public void BrowserLaunch(String Browser) {
		
		switch (Browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.err.println("Invalid browser: " + Browser);
			break;
		}
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@Parameters("sleeptime")
	@AfterTest
	public void Teardown(Long sleeptime) throws Exception {
		System.out.println("Sleeping for: " + sleeptime + " milliseconds");
		Thread.sleep(sleeptime);
	}

	@Parameters("url")
	@Test(priority = 1)
	public void LaunchUrl(String url) throws Exception {
		driver.get(url);
		Thread.sleep(2000); // Consider using wait instead of Thread.sleep
	}

	@Parameters({ "UserId", "PasswordId" })
	@Test(priority = 2)
	// Login Screen
	public void LoginDetails(String UserId, String PasswordId) throws Exception {
		WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		usernameField.sendKeys(UserId);
		driver.findElement(By.id("password")).sendKeys(PasswordId);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='captchaText']")).sendKeys("gfvc");

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Login')]")));
		loginButton.click();
		// Registration menu
		WebElement registrationMenu = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#m-4']")));
		registrationMenu.click();
		Thread.sleep(3000);
	}

	@Parameters({ "HospitalName", "Address", "Pincode", "Email", "FirstName", "LastName", "PhoneNumber",
			"RegistrationNo" })
	@Test(priority = 3)
	public void HospitalRegistrationScreen(String HospitalName, String Address, String Pincode, String Email,
			String FirstName, String LastName, String PhoneNumber, String RegistrationNo) throws Exception {
		// HospitalRegistration
		driver.findElement(By.xpath("//a[@href='/home/hospitalReg/hospitalDetailList']")).click();

		WebElement newRegistrationButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@class='btn btn-blue btn-icon float-right ng-star-inserted']")));
		newRegistrationButton.click();
		Thread.sleep(2000);

		Select locationType = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("locationtype_id"))));
		locationType.selectByVisibleText("Urban");

		Select state = new Select(driver.findElement(By.id("state_id")));
		state.selectByVisibleText("Haryana");
		Thread.sleep(2000);

		Select district = new Select(driver.findElement(By.id("district_id")));
		district.selectByVisibleText(ConstantUtils.district_id);

		driver.findElement(By.id("hospital_name")).sendKeys(HospitalName);
		driver.findElement(By.id("address")).sendKeys(Address);
		driver.findElement(By.id("pin_code")).sendKeys(Pincode);
		driver.findElement(By.id("contact_person_email")).sendKeys(Email);
		driver.findElement(By.id("contact_person_first_name")).sendKeys(FirstName);
		driver.findElement(By.id("contact_person_last_name")).sendKeys(LastName);
		driver.findElement(By.id("contact_person_phone_number")).sendKeys(PhoneNumber);
		driver.findElement(By.id("registration_no")).sendKeys(RegistrationNo);
		Thread.sleep(3000);

		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("window.scrollBy(0,500)");

		driver.findElement(By.xpath("//span[@class='dropdown-btn']")).click();
		Thread.sleep(2000);

		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[contains(text(),'Cardiology')]"));
		System.out.println("Total checkboxes: " + checkboxes.size());

		for (WebElement checkbox : checkboxes) {
			wait.until(ExpectedConditions.visibilityOf(checkbox));
			System.out.println("Checkbox text: " + checkbox.getText());
			jse1.executeScript("arguments[0].click();", checkbox);
			Thread.sleep(4000);
		}

		// Scroll down further
		jse1.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		WebElement hospitalH = driver.findElement(By.xpath("//input[@formcontrolname='regFile']"));
		hospitalH.sendKeys("C:\\Users\\Hariprasad R\\Downloads\\images.png");

		WebElement submitButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Submit']")));
		submitButton.click();
		Thread.sleep(3000);
	}

	@Parameters({ "TypeOfDoctor", "HospitalName", "FirstName", "LastName", "DOB", "Gender", "MobileNumber", "Email",
			"MCInumber", "LocationName", "DistrictName", "DepartmentName", "Pincode", "Qualification",
			"YearofExperience", "RegistraionExpiryDate", "Workshift" })
	@Test(priority = 4)
	public void DoctorRegistration(String TypeOfDoctor, String HospitalName, String FirstName, String LastName,
			String DOB, String Gender, String MobileNumber, String Email, String MCInumber, String LocationName,
			String DistrictName, String DepartmentName, String Pincode, String Qualification, String YearofExperience,
			String RegistraionExpiryDate, String Workshift) throws Exception {
		// Doctor Registration

		WebElement Doctorscreen = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Doctor Registration')]")));
		Doctorscreen.click();

		WebElement AddD = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@class='btn btn-blue btn-icon float-right ng-star-inserted']")));
		AddD.click();

		Select TypeofDoctor = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("doctor_type_id"))));
		TypeofDoctor.selectByVisibleText(TypeOfDoctor);
		Thread.sleep(2000);

		Select Hospitalname = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hospital_name"))));
		Hospitalname.selectByVisibleText(HospitalName);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@formcontrolname='first_name']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@formcontrolname='last_name']")).sendKeys(LastName);
		driver.findElement(By.id("birth_date")).sendKeys(DOB);
		Select GenderType = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gender_id"))));
		GenderType.selectByVisibleText(Gender);
		Thread.sleep(2000);

		driver.findElement(By.id("mobile")).sendKeys(MobileNumber);
		driver.findElement(By.id("email")).sendKeys(Email);
		driver.findElement(By.id("mci_number")).sendKeys(MCInumber);

		Select StateD = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("state_id"))));
		StateD.selectByVisibleText("Haryana");

		Select LocationTypeD = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("location_type_id"))));
		LocationTypeD.selectByVisibleText(LocationName);

		Select DistrictD = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("district_id"))));
		DistrictD.selectByVisibleText(DistrictName);

		Select DepartmentD = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("department_id"))));
		DepartmentD.selectByVisibleText(DepartmentName);

		driver.findElement(By.id("pin_code")).sendKeys(Pincode);
		Thread.sleep(2000);

		JavascriptExecutor jse4 = (JavascriptExecutor) driver;
		jse4.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);

		Select QualificationD = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("qualification_id"))));
		QualificationD.selectByVisibleText(Qualification);
		Thread.sleep(2000);

		driver.findElement(By.id("years_experience")).sendKeys(YearofExperience);
		driver.findElement(By.id("reg_expiry_date")).sendKeys(RegistraionExpiryDate);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//ng-multiselect-dropdown[@formcontrolname='doctor_languages']")).click();
		Thread.sleep(2000);

		List<WebElement> list1 = driver.findElements(By.xpath("//div[normalize-space()='English']"));
		Thread.sleep(3000);

		// Initialize JavaScript Executor
		JavascriptExecutor jse5 = (JavascriptExecutor) driver;

		// Print the size of the list
		System.out.println("Total checkboxes: " + list1.size());

		// Iterate through each CheckBox element
		for (WebElement chk1 : list1) {

			// Wait until the CheckBox is visible
			wait.until(ExpectedConditions.visibilityOf(chk1));

			// Print the CheckBox text
			System.out.println("Checkbox text: " + chk1.getText());

			// Use JavaScript to click the CheckBox
			jse5.executeScript("arguments[0].click();", chk1);

			// text field value
			chk1.getAttribute("English");
			Thread.sleep(2000);

			Select workshiftD = new Select(
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("work_shift_id"))));
			workshiftD.selectByVisibleText(Workshift);
			Thread.sleep(2000);

			WebElement Doctorsignature = driver.findElement(By.id("digitalCertificate"));
			Doctorsignature.sendKeys("C:\\Users\\Hariprasad R\\Downloads\\images.png");
			Thread.sleep(2000);

			WebElement HQCertificate = driver.findElement(By.id("mciCertificate"));
			HQCertificate.sendKeys("C:\\Users\\Hariprasad R\\Downloads\\OIP (1).jpg");
			Thread.sleep(2000);

			WebElement submitButtond = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//input[@class='btn btn-sm btn-primary ng-star-inserted']")));
			submitButtond.click();
			Thread.sleep(3000);
		}
	}

	@Parameters({ "AgentType", "FirstName", "LastName", "DOB", "Gender", "MobileNumber", "Email", "LocationType",
			"DistrictName", "Pincode", "Address", "WorkShift", "AadharUpload" })
	@Test
	public void HCWRegistration(String AgentType, String FirstName, String LastName, String DOB, String Gender,
			String MobileNumer, String Email, String LocationType, String DistrictName, String Pincode, String Address,
			String WorkShift, String AadharUpload) throws Exception {
		// HCW Registration
		WebElement HCWRegistrationscreen = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'HCW Registration')]")));
		HCWRegistrationscreen.click();

		WebElement HCWRegistrationAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id=\"layoutSidenav_content\"]/main/div[2]/app-agent-list/div/div/div/div/div/div[1]/button")));
		HCWRegistrationAdd.click();

		Select Typeofhealthassociate = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("agent_type_id"))));
		Typeofhealthassociate.selectByVisibleText(AgentType);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@formcontrolname='first_name']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@formcontrolname='last_name']")).sendKeys(LastName);
		driver.findElement(By.id("birth_date")).sendKeys(DOB);
		Select GenderTypeHCW = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gender_id"))));
		GenderTypeHCW.selectByVisibleText(Gender);
		Thread.sleep(2000);

		driver.findElement(By.id("mobile")).sendKeys(MobileNumer);
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(Email);

		Select StateHCW = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("state_id"))));
		StateHCW.selectByVisibleText("Haryana");

		Select LocationTypeHCW = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("location_type_id"))));
		LocationTypeHCW.selectByVisibleText(LocationType);

		Select DistrictHCW = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("district_id"))));
		DistrictHCW.selectByVisibleText(DistrictName);

		driver.findElement(By.xpath("//input[@formcontrolname='pin_code']")).sendKeys(Pincode);
		driver.findElement(By.xpath("//textarea[@formcontrolname='address']")).sendKeys(Address);

		JavascriptExecutor jse6 = (JavascriptExecutor) driver;
		jse6.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);

		Select workshiftHCW = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("work_shift_id"))));
		workshiftHCW.selectByVisibleText(WorkShift);
		Thread.sleep(2000);

		WebElement AadhaarIDHCW = driver.findElement(By.id("aadhaar"));
		AadhaarIDHCW.sendKeys(AadharUpload);
		Thread.sleep(2000);

		WebElement submitButtonHCW = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Submit']")));
		submitButtonHCW.click();
		Thread.sleep(3000);
	}

	@Parameters({ "HospitalName", "FirstName", "LastName", "Gender", "DOB", "MobileNumber", "Email", "Pincode",
			"Address", "Qualification", "YearofExperience", "RegistrationExpiryDate", "Workshift", "UploadVLDAPhoto",
			"QualificationCertificate", "DigitalCertificate" })
	@Test
	public void VLDAregistration(String HospitalName, String FirstName, String LastName, String Gender, String DOB,
			String MobileNumber, String Email, String Pincode, String Address, String Qualification,
			String YearofExperience, String RegistrationExpiryDate, String Workshift, String UploadVLDAPhoto,
			String QualificationCertificate, String DigitalCertificate) throws Exception {
		// VLDA Registration
		WebElement VLDAScreen = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[contains(text(),'Health Worker Registration')]")));
		VLDAScreen.click();

		WebElement VLDAAdd = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@title='Add']")));
		VLDAAdd.click();
		Thread.sleep(3000);

		Select hospitalSelect = new Select(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='hospital_id']"))));
		hospitalSelect.selectByVisibleText(HospitalName);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@formcontrolname='first_name']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@formcontrolname='last_name']")).sendKeys(LastName);
		Select GenderTypeVLDA = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gender_id"))));
		GenderTypeVLDA.selectByVisibleText(Gender);
		driver.findElement(By.id("birth_date")).sendKeys(DOB);
		driver.findElement(By.id("mobile")).sendKeys(MobileNumber);
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(Email);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@formcontrolname='pincode']")).sendKeys(Pincode);
		driver.findElement(By.xpath("//textarea[@formcontrolname='home_address']")).sendKeys(Address);

		Select QualificationVLDA = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("qualification_id"))));
		QualificationVLDA.selectByVisibleText(Qualification);
		Thread.sleep(3000);

		driver.findElement(By.id("years_experience")).sendKeys(YearofExperience);
		driver.findElement(By.id("registration_exp_date")).sendKeys("25-09-2030");

		driver.findElement(By.xpath("//ng-multiselect-dropdown[@formcontrolname='health_worker_languages']")).click();

		List<WebElement> listVlda = driver.findElements(By.xpath("//div[contains(text(),'English')]"));
		JavascriptExecutor jse8 = (JavascriptExecutor) driver;

		// Print the size of the list
		System.out.println("Total checkboxes: " + listVlda.size());

		// Iterate through each CheckBox element
		for (WebElement chk2 : listVlda) {

			// Wait until the CheckBox is visible
			wait.until(ExpectedConditions.visibilityOf(chk2));

			// Print the CheckBox text
			System.out.println("Checkbox text: " + chk2.getText());

			// Use JavaScript to click the CheckBox
			jse8.executeScript("arguments[0].click();", chk2);

			// text field value
			chk2.getAttribute("English");
			Thread.sleep(2000);

			Select workshiftDriver = new Select(
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("work_shift_id"))));
			workshiftDriver.selectByVisibleText(Workshift);
			Thread.sleep(2000);

			JavascriptExecutor jse15 = (JavascriptExecutor) driver;
			jse15.executeScript("window.scrollBy(0,250)");

			JavascriptExecutor jse16 = (JavascriptExecutor) driver;
			jse16.executeScript("window.scrollBy(0,250)");
			Thread.sleep(2000);

			WebElement VLDAPhoto = driver.findElement(By.xpath("//input[@id='photo']"));
			// Send the file path to the input element for upload
			VLDAPhoto.sendKeys("C:\\Users\\Hariprasad R\\Downloads\\OIP (1).jpg");

			WebElement fileInput = driver.findElement(By.xpath("//input[@id='qualCertificate']"));
			// Send the file path to the input element for upload
			fileInput.sendKeys("C:\\Users\\Hariprasad R\\Downloads\\OIP.jpg");

			WebElement DSVLDA = driver.findElement(By.xpath("//input[@id='digitalCertificate']"));
			DSVLDA.sendKeys("C:\\Users\\Hariprasad R\\Downloads\\images.png");

			JavascriptExecutor jse3 = (JavascriptExecutor) driver;
			jse3.executeScript("window.scrollBy(0,250)");

			JavascriptExecutor jse14 = (JavascriptExecutor) driver;
			jse14.executeScript("window.scrollBy(0,250)");
			Thread.sleep(2000);

			WebElement element = driver.findElement(By.xpath(" //div[@class='modal-footer']//input[@value='Submit']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
		}
	}

	@Parameters({ "FirstName", "LastName", "EmployeeType", "BranchName", "LocationType", "DistrictName", "MandalName",
			"VillageName", "Pincode", "MobileNumber", "Email", "LicenceNumber", "HeavyvechicleLicens", "Address",
			"WorkShift", "PoliceVerification" })
	@Test
	public void DriverRegistration(String FirstName, String LastName, String EmployeeType, String BranchName,
			String LocationType, String DistrictName, String MandalName, String VillageName, String Pincode,
			String MobileNumber, String Email, String LicenceNumber, String HeavyvechicleLicens, String Address,
			String WorkShift, String PoliceVerification) throws Exception {
		// Driver Registration
		WebElement driverRegistration = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[normalize-space()='Driver Registration']")));
		driverRegistration.click();
		Thread.sleep(2000);
		WebElement addDriver = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-plus']")));
		addDriver.click();

		driver.findElement(By.id("first_name")).sendKeys("UshaVSK");
		driver.findElement(By.id("last_name")).sendKeys("Driver");

		Select employeeType = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("employee_type"))));
		employeeType.selectByVisibleText("Permanent");

		Select genderDriver = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("branch_name"))));
		genderDriver.selectByVisibleText("Female");
		Thread.sleep(2000);
		// Set Driver date of birth details
		String month = "September";
		String year = "1999";
		String day = "16";

		// Open date picker

		WebElement dobInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='dob']")));
		dobInput.click();

		WebElement dobInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='dob']")));
		dobInput1.click();
		// Loop to navigate to the correct month and year
		while (true) {
			WebElement monthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"nav-f\"]/bs-datepicker-container/div/div/div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/bs-datepicker-navigation-view/button[2]")));
			String monthText = monthElement.getText();

			WebElement yearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"nav-f\"]/bs-datepicker-container/div/div/div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/bs-datepicker-navigation-view/button[3]")));
			String yearText = yearElement.getText();

			if (monthText.equals(month) && yearText.equals(year)) {
				break;
			} else {
				WebElement prevButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='previous']")));
				prevButton.click();
			}
		}

		// Select the specific day
		WebElement dayElement = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + day + "')]")));
		dayElement.click();
		Thread.sleep(2000);

		// Set Date of Joining Details Driver
		String month1 = "June";
		String Year1 = "2021";
		String Date = "15";
		wait.until(ExpectedConditions.elementToBeClickable(By.id("date_of_joining"))).click();

		while (true) {
			WebElement MonthElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"nav-f\"]/bs-datepicker-container[1]/div/div/div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/bs-datepicker-navigation-view/button[2]")));
			String monthText1 = MonthElement1.getText();

			WebElement YearElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"nav-f\"]/bs-datepicker-container[1]/div/div/div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/bs-datepicker-navigation-view/button[3]/span")));
			String YearText1 = YearElement1.getText();

			if (monthText1.equals(month1) && YearText1.equals(Year1)) {
				break;
			} else {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='previous']"))).click();
			}
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + Date + "')]")))
				.click();

		// Location Type
		Select LocationTypeDriver = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("location_type_id"))));
		LocationTypeDriver.selectByVisibleText("Rural");

		Select DistrictDriver = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("district_id"))));
		DistrictDriver.selectByVisibleText("Ambala");
		Thread.sleep(2000);

		Select MandalDriver = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mandal_id"))));
		MandalDriver.selectByVisibleText("Ambala Cantonment");

		Select VillageDriver = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("village_id"))));
		VillageDriver.selectByVisibleText("Barnala (Part)(48)");

		driver.findElement(By.xpath("//input[@formcontrolname='pin_code']")).sendKeys("500058");

		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("7875867567");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ushadriver@dhspltd.com");

		JavascriptExecutor jse7 = (JavascriptExecutor) driver;
		jse7.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='licence_number']")).sendKeys("TSh9876655");

		JavascriptExecutor jse18 = (JavascriptExecutor) driver;
		jse18.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		// License Expire Date
		String MONTH = "September";
		String YEAR = "2030";
		String DATE = "25";

		// Click the date input field to open the date picker
		WebElement LicenseExpireDate = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='licence_expiry_date']")));
		LicenseExpireDate.click();

		// Loop to navigate through months and years in the date picker
		while (true) {
			// Locate and get the current month and year displayed in the date picker
			WebElement CurrentMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"nav-f\"]/bs-datepicker-container/div/div/div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/bs-datepicker-navigation-view/button[2]/span")));
			String MonthText = CurrentMonth.getText();

			WebElement CurrentYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"nav-f\"]/bs-datepicker-container[1]/div/div/div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/bs-datepicker-navigation-view/button[3]/span")));
			String YearText = CurrentYear.getText();

			// Check if the current month and year match the desired values
			if (MonthText.equals(MONTH) && YearText.equals(YEAR)) {
				break;
			} else {
				// Click the "next" button to go to the next month if the month and year don't
				// match
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'next')]")))
						.click();
			}
		}

		// Once the correct month and year are displayed, select the desired date
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + DATE + "')]")))
				.click();

		driver.findElement(By.id("heavy_vehicle_license")).sendKeys("TSH00987655566");

		driver.findElement(By.xpath("//textarea[@formcontrolname='address']")).sendKeys("Ambala");

		Select WorkshiftDriver = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='work_shift_id']"))));
		WorkshiftDriver.selectByVisibleText("24/7 (11:15 - 23:59)");
		Thread.sleep(3000);

		WebElement PVCDriver = driver.findElement(By.xpath("//input[@id='policeVer']"));
		PVCDriver.sendKeys("C:\\Users\\Hariprasad R\\Downloads\\Blood sugar Test.pdf");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"nav-f\"]/ngb-modal-window/div/div/app-driver-registration/div[3]/input"))
				.click();
	}

	@Parameters({ "CampName", "CampType", "DurationCampHours", "DurationCampMintues", "Campdistrict", "BaseLocation",
			"Address", "Latitude", "Longitude", "InchargeName", "InchargeMobileNumber" })
	@Test
	public void CampRegistration(String CampName, String CampType, String DurationCampHours, String DurationCampMintues,
			String Campdistrict, String BaseLocation, String Address, String Latitude, String Longitude,
			String InchargeName, String InchargeMobileNumber) throws Exception {
		// Demo (Camp Creation)
		WebElement DemoScreen = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Demo Creation')]")));
		DemoScreen.click();
		Thread.sleep(2000);

		WebElement DemoScreenAddbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id=\"layoutSidenav_content\"]/main/div[2]/app-camp-creation/div[1]/div/div/div/div/div[1]/button")));
		DemoScreenAddbutton.click();

		driver.findElement(By.xpath("//input[@formcontrolname='camp_name']")).sendKeys(CampName);

		driver.findElement(By.xpath("//input[@formcontrolname='camp_type_id']")).sendKeys(CampType);

		driver.findElement(By.xpath(
				"//*[@id=\"nav-f\"]/ngb-modal-window/div/div/app-camp-creation-add/div[2]/div/form/div/div[3]/div/span/input[1]"))
				.clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//*[@id=\"nav-f\"]/ngb-modal-window/div/div/app-camp-creation-add/div[2]/div/form/div/div[3]/div/span/input[1]"))
				.sendKeys(DurationCampHours);

		driver.findElement(By.xpath(
				"//*[@id=\"nav-f\"]/ngb-modal-window/div/div/app-camp-creation-add/div[2]/div/form/div/div[3]/div/span/input[2]"))
				.clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//*[@id=\"nav-f\"]/ngb-modal-window/div/div/app-camp-creation-add/div[2]/div/form/div/div[3]/div/span/input[2]"))
				.sendKeys(DurationCampMintues);

		Select CampDistrict = new Select(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='district_id']"))));
		CampDistrict.selectByValue(Campdistrict);

		Select CampBaseLocation = new Select(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='baselctn_id']"))));
		CampBaseLocation.selectByValue(BaseLocation);

		driver.findElement(By.xpath("//input[@formcontrolname='location']")).sendKeys(Address);

		driver.findElement(By.xpath("//input[@formcontrolname='latitude']")).sendKeys(Latitude);

		driver.findElement(By.xpath("//input[@formcontrolname='longitude']")).sendKeys(Longitude);

		driver.findElement(By.xpath("//input[@formcontrolname='incharge']")).sendKeys(InchargeName);

		driver.findElement(By.xpath("//input[@formcontrolname='incharge_mobile']")).sendKeys(InchargeMobileNumber);
		Thread.sleep(2000);

		driver.findElement(
				By.xpath("//app-camp-creation-add[@class='component-host-scrollable']//input[@value='Submit']"))
				.click();

	}

}


