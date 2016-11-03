package automatedTest;
 
 
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
 
 
public class MainTest {
    
	public static void main(String[] args){
		MainTest t = new MainTest();
		t.initialize();
		t.test();
		t.end();
	}
	
    public void initialize(){
        System.out.println("- - - - - Starting Test  - - - - -\n");
        //get data from csv
    }
   
   
    
    public void test(){
    	HashMap<String, String> parameters = new HashMap<String, String>();
    	parameters.put("CEP", "50980360");
    	parameters.put("CPF", GerarCPF.geraCPF());
    	parameters.put("nome", "Jose Joao Silva");
    	parameters.put("dataNascimento", "20101950");
    	parameters.put("celularDDD", "11");
    	parameters.put("celularNumero", "998989898");
    	parameters.put("telefoneDDD", "11");
    	parameters.put("telefoneNumero", "32323232");
    	parameters.put("email", "joaojosesilva@gmail.com");
    	
    	
    	
    	
    	parameters.put("anoFabricacao", "2008");
    	parameters.put("modelo", "2008");
    	parameters.put("veiculo", "COROLLA SEDAN XEI 1.8 16V(N.SERIE ) AUT. FLEX A/G 4P");
    	parameters.put("uso", "Particular");
    	parameters.put("profissao", "50980360");
    	parameters.put("tempoHabilitacao", "10");
    	parameters.put("tipoResidencia", "Apartamento");
    	parameters.put("principalCondutorReside", "Sim");
    	parameters.put("principalCondutorPossuiFilhos", "Sim");
    	parameters.put("veiculosNaResidencia", "2");
    	parameters.put("CEPlocal", "50980360");
    	parameters.put("relacao", "Outros");
    	parameters.put("veiculoAlienado", "Não");
    	parameters.put("utilizadoMaisDe2Dias", "Não");
    	parameters.put("dispositivoAntiFurto", "Não");
    	parameters.put("garagem", "Não");
    	
    	BudgetTestMapfra t1 = new BudgetTestMapfra("chrome", parameters);
    	t1.run();
    	
//    	(new Thread(new BudgetTestMapfra("chrome", parameters))).run(); // Valid Input
//        (new Thread(new CepTest("01311300", true, false, "Valid Input"))).run(); // Valid Input  
//        (new Thread(new CepTest("00000000", false, false, "Invalid Input"))).run(); // Invalid Input
//        (new Thread(new CepTest("60520660", true, true, "Desm Input"))).run(); // Desm. Input
        
        
    }
   
    
    public synchronized void end(){
    	// export results to csv
        System.out.println("\n- - - - - Test finished - - - - -");
    }
   
   
}
 
 
 
