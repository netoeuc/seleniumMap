package automatedTest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class AutomatedTest {
	String cep;
	boolean valid;
	boolean desm;
	WebDriver chromeDriver;
	WebElement cepImg;
	WebElement textField;
	WebElement buttonField;
	WebElement labelReturn;
	
	
	
	public static void main(String[] args) {
		AutomatedTest at = new AutomatedTest();
		at.test();
	}
	
	@Before
	public void initialize(){
		cep = "60520660";
		valid = true;
		desm = true;
		
		System.setProperty("webdriver.chrome.driver", "/Users/neto/Documents/eclipse/Alura/TesteDeAplicacao/lib/chromedriver");
		chromeDriver = new ChromeDriver();
		chromeDriver.get("http://www.correios.com.br/para-voce");
		cepImg = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/ul/li[1]/a/img"));
		cepImg.click();
		textField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/span[2]/label/input"));
		buttonField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/div[6]/input"));
		chromeDriver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/");
		textField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/span[2]/label/input"));
		buttonField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/div[6]/input"));
		textField.sendKeys(cep);
		buttonField.click();
	}
	
	
	@Test
	public void test(){
		System.out.println(valid);
		if(valid){
			if(!desm){
				System.out.println("Valid (cep)");
				// Valid Input
				labelReturn = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/p"));
				assertTrue(labelReturn.getText().equals("DADOS ENCONTRADOS COM SUCESSO."));
				System.out.println("(1) Pass! (label 'DADOS ENCONTRADOS COM SUCESSO')");
				// Testing table return
				WebElement logradouroLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[1]"));
				WebElement bairroLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[2]"));
				WebElement localidadeLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[3]"));
				WebElement cepLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[4]"));
				
				assertTrue(logradouroLabel.getText().equals("Logradouro/Nome:"));
				assertTrue(bairroLabel.getText().equals("Bairro/Distrito:"));
				assertTrue(localidadeLabel.getText().equals("Localidade/UF:"));
				assertTrue(cepLabel.getText().equals("CEP:"));
				System.out.println("(2) Pass! (elements from table)");
				
				WebElement cepRetorno = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]"));
				assertTrue(cepRetorno.getText().equals(cep.substring(0,5)+"-"+cep.substring(5)));
				System.out.println("(3) Pass! (label 'cepReturn')");
			}else{
				System.out.println("Desm (cep)");
				// Testing message
				WebElement cepReturn = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/p"));
				assertTrue(cepReturn.getText().equals("ATENÇÃO! O CEP "+cep.substring(0,5)+"-"+cep.substring(5)+" FOI DESMEMBRADO CONFORME ABAIXO."));
				System.out.println("(1) Pass! (label 'ATENÇÃO! O CEP "+cep.substring(0,5)+"-"+cep.substring(5)+" FOI DESMEMBRADO CONFORME ABAIXO.')");
				
			}
		}else{
			System.out.println("Invalid (cep)");
			// Invalid Input
			labelReturn = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/p"));
			assertTrue(labelReturn.getText().equals("DADOS NAO ENCONTRADOS"));
			System.out.println("(1) Pass! (label 'DADOS NAO ENCONTRADOS')");
			
		}
		
	}
	
	@After
	public void close(){
		chromeDriver.close();
	}

}



