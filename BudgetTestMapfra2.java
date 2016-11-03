package automatedTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class BudgetTestMapfra2 implements Runnable, Testable {
	private String browser;
	private HashMap<String, String> parameters;
	
	public BudgetTestMapfra2(String b, HashMap<String, String> p){
		this.browser = b;
		this.parameters = p;
	}
	
	@Override
	public void close(WebDriver driver) {
		driver.close();
		
		
	}

	@Override
	public WebDriver getWebDriver(String browser) {
		WebDriver driver = null;
		if(browser.equals("chrome")){
	        System.setProperty("webdriver.chrome.driver", "/Users/neto/Documents/eclipse/Alura/TesteDeAplicacao/lib/chromedriver");
	        driver = new ChromeDriver();
		}else if (browser.equals("firefox")){
//			System.setProperty("webdriver.chrome.driver", "C:/Users/treinamento.MW7TQ90VKVHLSV/Documents/Tiago Malaquias/TestAutomation/lib/chromedriver.exe");
	        driver = new FirefoxDriver();
		}
		driver.get("http://www.segurosmapfremais.com.br/mapfre/");
		return driver;
	}

	@Override
	public void testScenario() {
		WebDriver driver = this.getWebDriver(this.browser);
		WebElement textBoxCEPIntro = driver.findElement(By.xpath("//*[@id=\"field-1\"]")); // textField of CEP
		textBoxCEPIntro.sendKeys(this.parameters.get("CEP")); // Sending the CEP to the textField
		WebElement simulationButton = driver.findElement(By.xpath("//*[@id=\"botao\"]")); // simulation Button
		simulationButton.click();
		WebElement calcButton = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div[1]/div[2]/div[1]/div/a[2]")); // in order to calc the budget
		calcButton.click();
		
		
		
		while(driver.getWindowHandles().size()<2){}
		System.out.println("saiu");
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles()); // getting all tabs from the browser
	    driver.switchTo().window(tabs2.get(1)); // setting the new tab to be the current tab
	    
	    
		
		// Page 1
		try{

			driver.switchTo().frame(0);
			driver.switchTo().frame(0);
			
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/span/div/div/form/section/div/div[1]/span/div/span[1]/div/div[1]")));
			
			
			
			
			System.out.println("inicio definicao");
			WebElement cpfTextField = driver.findElement(By.xpath("/html/body/div[1]/span/div/div/form/section/div/div[1]/span/div/span[1]/div/div[1]")); // textField of CPF
			
			System.out.println("fimDefinicao");
			
			System.out.println("inicioPreenchimento");
			fillField(cpfTextField, "CPF", driver);
			WebDriverWait wait2 = new WebDriverWait(driver, 50);
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formSeguroSegurado:panelCpf\"]/div/div[2]")));
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"formSeguroSegurado:panelCpf\"]/div/div[2]")));
			
			WebElement nomeTextField = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:nomeProponente\"]")); // textField of Name
			WebElement dataNascimentoTextField = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:dataNascimento\"]")); // textField of Birth Date
			WebElement celularDDDTextField = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:dddCelular\"]")); // textField of celularDDD
			WebElement celularNumeroTextField = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:telefoneCelular\"]")); // textField of the mobile phone number
			WebElement telefoneDDDTextField = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:dddResidencial\"]")); // textField of phoneDDD
			WebElement telefoneNumeroTextField = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:telefoneResidencial\"]")); // textField of phone number
			WebElement emailTextField = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:email\"]")); // textField of email address
			WebElement calcularButton = driver.findElement(By.xpath("//*[@id=\"formSeguroSegurado:btoProximo\"]"));
			
			
			fillField(nomeTextField, "nome", driver); // just to let the site process
			fillField(dataNascimentoTextField, "dataNascimento", driver);
			fillField(celularDDDTextField, "celularDDD", driver);
			fillField(celularNumeroTextField, "celularNumero", driver);
			fillField(telefoneDDDTextField, "telefoneDDD", driver);
			fillField(telefoneNumeroTextField, "telefoneNumero", driver);
			fillField(emailTextField, "email", driver);
			System.out.println("fimPreenchimento");
			
			calcularButton.click();
			
			
		}catch(Exception e1){
			// Error in page 1 somewhere
			System.out.println("\n\n\n\n\n");
			System.out.println("Exception - the page may not be fully loaded.");
			e1.printStackTrace();
		}catch(Error e2){
			System.out.println("Error - There is a fail somewhere.");
			e2.printStackTrace();
		}
		driver.switchTo().defaultContent();
		
		// page 2
		try{

			driver.switchTo().frame(0);
			driver.switchTo().frame(0);
			
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formVeiculo:anoDeFabricacao\"]")));
			
			WebElement veiculo = driver.findElement(By.xpath("//*[@id=\"formVeiculo:veiculosInput\"]")); // textField of CPF
			
				
			
			
			veiculo.sendKeys(this.parameters.get("veiculo").subSequence(0, this.parameters.get("veiculo").length()-2));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formVeiculo:veiculosItems\"]/div")));
			veiculo.sendKeys(Keys.ENTER);
			
			veiculo.sendKeys(Keys.ARROW_DOWN);
		    veiculo.sendKeys(Keys.ENTER);
			
		    WebElement anoFabricacao = driver.findElement(By.xpath("//*[@id=\"formVeiculo:anoDeFabricacao\"]")); // textField of CPF
			WebElement anoModelo = driver.findElement(By.xpath("//*[@id=\"formVeiculo:modelo\"]")); // textField of CPF
			WebElement uso = driver.findElement(By.xpath("//*[@id=\"formVeiculo:uso\"]")); // textField of CPF
			
		    
		    final Select selectBoxAnoFabricacao = new Select(anoFabricacao);
			selectBoxAnoFabricacao.selectByValue(this.parameters.get("anoFabricacao"));
		    final Select selectBoxAnoModelo = new Select(anoModelo);
		    selectBoxAnoModelo.selectByValue(this.parameters.get("modelo"));
		    
		  
		    final Select selectBoxUso = new Select(uso);
		    uso.click();
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formVeiculo:uso\"]/option[2]")));
		    WebElement particular = driver.findElement(By.xpath("//*[@id=\"formVeiculo:uso\"]/option[2]")); // textField of CPF
			particular.click();
		  
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formVeiculo:isencaoImposto\"]")));
		    
			WebElement prox = driver.findElement(By.xpath("//*[@id=\"formVeiculo:btoProximo\"]")); // textField of CPF
			prox.click();
		    
		}catch(Exception e1){
			// Error in page 1 somewhere
			System.out.println("\n\n\n\n\n");
			System.out.println("Exception - the page may not be fully loaded.");
			e1.printStackTrace();
		}catch(Error e2){
			System.out.println("Error - There is a fail somewhere.");
			e2.printStackTrace();
		}
		driver.switchTo().defaultContent();
		
		
		// Page 3
//		try{
//			driver.switchTo().frame(0);
//			
//			driver.switchTo().frame(0);
//			WebDriverWait wait = new WebDriverWait(driver, 50);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formAvaliacaoRisco:btoProximo\"]")));
//			
//			
//			
//			
//			WebElement prox2 = driver.findElement(By.xpath("//*[@id=\"formAvaliacaoRisco:btoProximo\"]")); // textField of CPF
//			
////			WebElement simSegurado = driver.findElement(By.xpath("//*[@id=\"formAvaliacaoRisco:id_2935_CND_33_54774067450754649_0_0\"]")); // textField of CPF
////			simSegurado.click();
////			
////			WebElement sexoMasculino = driver.findElement(By.xpath("//*[@id=\"formAvaliacaoRisco:id_98_CND_33_54774054307754617_0_0:1\"]")); // textField of CPF
////			sexoMasculino.click();
//			
//			WebElement estadoCivil = driver.findElement(By.xpath("//*[@id=\"formAvaliacaoRisco:id_100_CND_33_54774066606754795_0_0\"]")); // textField of CPF
//			estadoCivil.click();
//			
//			
//			WebElement veiculo = driver.findElement(By.xpath("//*[@id=\"formVeiculo:veiculosInput\"]")); // textField of CPF
//			
//				
//			
//			
//			veiculo.sendKeys(this.parameters.get("veiculo").subSequence(0, this.parameters.get("veiculo").length()-2));
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formVeiculo:veiculosItems\"]/div")));
//			veiculo.sendKeys(Keys.ENTER);
//			
//			veiculo.sendKeys(Keys.ARROW_DOWN);
//		    veiculo.sendKeys(Keys.ENTER);
//			
//		    WebElement anoFabricacao = driver.findElement(By.xpath("//*[@id=\"formVeiculo:anoDeFabricacao\"]")); // textField of CPF
//			WebElement anoModelo = driver.findElement(By.xpath("//*[@id=\"formVeiculo:modelo\"]")); // textField of CPF
//			WebElement uso = driver.findElement(By.xpath("//*[@id=\"formVeiculo:uso\"]")); // textField of CPF
//			WebElement prox = driver.findElement(By.xpath("//*[@id=\"formVeiculo:btoProximo\"]")); // textField of CPF
//			
//		    
//		    final Select selectBoxAnoFabricacao = new Select(anoFabricacao);
//			selectBoxAnoFabricacao.selectByValue(this.parameters.get("anoFabricacao"));
//		    final Select selectBoxAnoModelo = new Select(anoModelo);
//		    selectBoxAnoModelo.selectByValue(this.parameters.get("modelo"));
//		    
//		  
//		    final Select selectBoxUso = new Select(uso);
//		    uso.click();
//		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"formVeiculo:uso\"]/option[2]")));
//		    WebElement particular = driver.findElement(By.xpath("//*[@id=\"formVeiculo:uso\"]/option[2]")); // textField of CPF
//			particular.click();
//		  
//			
//			
//			prox.click();
		    
//		}catch(Exception e1){
//			// Error in page 1 somewhere
//			System.out.println("\n\n\n\n\n");
//			System.out.println("Exception - the page may not be fully loaded.");
//			e1.printStackTrace();
//		}catch(Error e2){
//			System.out.println("Error - There is a fail somewhere.");
//			e2.printStackTrace();
//		}
//		driver.switchTo().defaultContent();
		
		
		
		
	}

	@Override
	public void run() {
		this.testScenario();
		
	}
	
	public void fillField(WebElement element, String key, WebDriver driver){
		try{
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.click();
			actions.sendKeys(this.parameters.get(key));
			actions.build().perform();
			if(key.equals("CPF")){
				actions.sendKeys(Keys.TAB);
				actions.build().perform();
			}
		}catch(Exception e){
			System.out.println("rec");
			System.out.println(key);
			fillField(element, key, driver);
		}
	}

}
