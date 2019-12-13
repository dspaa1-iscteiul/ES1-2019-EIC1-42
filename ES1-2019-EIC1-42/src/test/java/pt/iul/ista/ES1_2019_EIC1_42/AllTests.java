package pt.iul.ista.ES1_2019_EIC1_42;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pt.iul.ista.ES1_2019_EIC1_42.gui.Comparador_de_QualidadeTest;
import pt.iul.ista.ES1_2019_EIC1_42.gui.ExcelDataTest;
import pt.iul.ista.ES1_2019_EIC1_42.gui.Nova_RegraTest;

@RunWith(Suite.class)
@SuiteClasses({
	AppTest.class,
	CompareTableTest.class,
	Comparador_de_QualidadeTest.class,
	DataModelTest.class,
	ExcelDataTest.class,
	MetodoTest.class,
	Nova_RegraTest.class,
	RegrasModelTest.class,
	RegraTest.class,
	ResultadosIndicadoresModelTest.class,	
})

public class AllTests {
	
}


