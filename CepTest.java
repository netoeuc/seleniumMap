package automatedTest;
 
import static org.junit.Assert.assertTrue;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class CepTest implements Runnable, Testable{
    private String cep;
    private boolean valid;
    private boolean desm;
    private String description;
   
    public CepTest(String c, boolean v, boolean d, String descrip){
        this.cep = c;
        this.valid = v;
        this.desm = d;
        this.description = descrip;
    }
    public void run() {
        this.testInsertTest(cep, valid, desm);
    }
   
   
     public void close(WebDriver driver){
            driver.close();
        }
           
       
        public WebDriver getWebDriver(String browser){
            WebDriver chromeDriver;
            System.setProperty("webdriver.chrome.driver", "/Users/neto/Documents/eclipse/Alura/TesteDeAplicacao/lib/chromedriver");
            chromeDriver = new ChromeDriver();
            chromeDriver.get("http://www.correios.com.br/para-voce");
            return chromeDriver;
        }
       
        public void testInsertTest(String cep, boolean valid, boolean desm ){
            WebElement cepImg;
            WebElement textField;
            WebElement buttonField;
            WebElement labelReturn;
            WebDriver chromeDriver = this.getWebDriver("chrome");
           
            cepImg = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/ul/li[1]/a/img"));
            cepImg.click();
            textField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/span[2]/label/input"));
            buttonField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/div[6]/input"));
            chromeDriver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/");
            textField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/span[2]/label/input"));
            buttonField = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[3]/form/div/div/div[6]/input"));
            textField.sendKeys(cep);
            buttonField.click();
           
           
            if(valid){
                if(!desm){
                    System.out.println("("+this.description+") Valid (cep)");
                    // Valid Input
                    labelReturn = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/p"));
                    try{
                        assertTrue(labelReturn.getText().equals("DADOS ENCONTRADOS COM SUCESSO."));
                        System.out.println("("+this.description+") (1) Pass! (label 'DADOS ENCONTRADOS COM SUCESSO')");
                    }catch (Error e){
                        System.out.println("("+this.description+") (1) Fail! (label 'DADOS ENCONTRADOS COM SUCESSO')");
                    }
                        // Testing table return
                    WebElement logradouroLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[1]"));
                    WebElement bairroLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[2]"));
                    WebElement localidadeLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[3]"));
                    WebElement cepLabel = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/th[4]"));
                   
                    try{
                        assertTrue(logradouroLabel.getText().equals("Logradouro/Nome:"));
                        assertTrue(bairroLabel.getText().equals("Bairro/Distrito:"));
                        assertTrue(localidadeLabel.getText().equals("Localidade/UF:"));
                        assertTrue(cepLabel.getText().equals("CEP:"));
                        System.out.println("("+this.description+") (2) Pass! (elements from table)");
                    }catch (Error e){
                        System.out.println("("+this.description+") (2) Fail! (elements from table)");
                    }
                   
                   
                    WebElement cepRetorno = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]"));
                    try{
                        assertTrue(cepRetorno.getText().equals(cep.substring(0,5)+"-"+cep.substring(5)));
                        System.out.println("("+this.description+") (3) Pass! (cepReturn)");
                    }catch (Error e){
                        System.out.println("("+this.description+") (3) Fail! (cepReturn)");
                    }
                }else{
                    System.out.println("("+this.description+") Desm (cep)");
                    // Testing message
                    WebElement cepReturn = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/p"));
                    try{
                        assertTrue(cepReturn.getText().equals("ATENÇÃO! O CEP "+cep.substring(0,5)+"-"+cep.substring(5)+" FOI DESMEMBRADO CONFORME ABAIXO."));
                        System.out.println("("+this.description+") (1) Pass! (label 'ATENÇÃO! O CEP "+cep.substring(0,5)+"-"+cep.substring(5)+" FOI DESMEMBRADO CONFORME ABAIXO.')");
                    }catch (Error e){
                        System.out.println("("+this.description+") (1) Fail! (label 'ATENÇÃO! O CEP "+cep.substring(0,5)+"-"+cep.substring(5)+" FOI DESMEMBRADO CONFORME ABAIXO.')");
                    }
                   
                }
            }else{
                System.out.println("("+this.description+") Invalid (cep)");
                // Invalid Input
                labelReturn = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/p"));
                try{
                    assertTrue(labelReturn.getText().equals("DADOS NAO ENCONTRADOS"));
                    System.out.println("("+this.description+") (1) Pass! (label 'DADOS NAO ENCONTRADOS')");
                }catch (Error e){
                    System.out.println("("+this.description+") (1) Fail! (label 'DADOS NAO ENCONTRADOS')");
                   
                }
               
            }
            close(chromeDriver);
        }
		@Override
		public void testScenario() {
			// TODO Auto-generated method stub
			
		}
 
}
 
 
 
 
 
